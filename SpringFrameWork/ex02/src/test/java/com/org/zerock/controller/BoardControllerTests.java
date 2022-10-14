package com.org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.org.zerock.mapper.BoardMapperTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)

// 컨트롤러 테스트를 하기 위해서 테스트 환경을 구축
// - servlet-context.xml(컨트롤러 탐색 패키지 경로)의 설정 정보를 읽음
// - ApplicationContext(메모리)에 로딩해야 함
// - @Controller 어노테이션을 보고, 컨트롤러를 메모리에 로딩
@WebAppConfiguration

// root-context.xml => 서비스, mapper 설정을 위해서
// servlet-context.xml => 컨트롤러의 테스트 환경 설정
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j2

public class BoardControllerTests {
	
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx; // 테스트 환경을 준비하는 메모리 객체
	
	// 아래의 테스트 내용을 톰켓 없이 수행해야 함
	// => request-get, 매개변수 -> response -> 포워딩(path)
	// => response-post, 매개변수 -> response -> 포워딩(path)
	
	private MockMvc mockMvc; // 가상의 request, response 통신을 수행 가상의 mvc, 가짜로 url과 파라미터등을 브라우저에서 사용하는 것처럼 만들어서 Controller를 실행해 볼수 있음
	
	// 테스트 시작 전에 명시적으로 테스트 환경을 설정하도록 메소드 정의
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		// MockMvc를 이용해서 가상의 request 통신을 생성
		// get 방식으로 요청
		// 요청 path는 "/board/list" => BoardController.list()로 매핑
		// 전체 게시글 조회 서비스를 수행하게 됨
		
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());// 메서드 체인
		
	}
	
	@Test
	public void testRegister() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트 새글 제목")
				.param("content", "테스트 새글 내용")
				.param("writer", "user00")).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);		

	}
	
	@Test
	public void testGet() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				.param("bno", "11"))
				.andReturn()
				.getModelAndView().getModelMap());	
	}
	
	@Test
	public void testModify() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "15")
				.param("title", "수정된 테스트 새글 제목")
				.param("content", "수정된 테스트 새글 내용")
				.param("writer", "user00"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
	
	@Test
	public void testRemove() throws Exception{
		// 삭제전 데이터베이스에 게시물 번호 확인할 것
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "10"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
	
	
	
	
	
	
}
