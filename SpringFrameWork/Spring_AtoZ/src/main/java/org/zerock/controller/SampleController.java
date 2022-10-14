package org.zerock.controller;

import java.net.http.HttpHeaders;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/sample/*")
@Log4j2
public class SampleController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/ex03") // /sample/ex03?title=testTitle&dueDate=2022-10-13
	public String ex03(TodoDTO todo) {
		log.info(todo);
		return "ex03";
	}
	
	@RequestMapping(value="/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basic() {
		log.info("");
	}
	
	@GetMapping("/basicOnlyGet") // get 방식만 요청처리함
	public void basicGet2() {
		
	}
	
	//http://localhost:8080/sample/ex01?name=홍길동&age=30
	@GetMapping("/ex01") // get 방식만 요청처리함
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
	
	// http://localhost:8080/sample/ex02?name=홍길동&age=30
	@GetMapping("/ex02") // get 방식만 요청처리함
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name : " + name);
		log.info("age : " + age);
		return "ex02";
	}
	
	@GetMapping("/ex02List") // /sample/ex02List?ids=111&ids=222&ids=333
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		// ArrayList로 매개변수 바인딩 처리
		log.info("ids : ",ids);
		
		return "ex02List";
	}
	
	@GetMapping("/ex02Array") // /sample/ex02List?ids=111&ids=222&ids=333
	public String ex02List(@RequestParam("ids") String[] ids) {
		// 배열 형태로 매개변수 바인딩 처리
		log.info("Array ids : ",Arrays.toString(ids));
		
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean") // /sample/ex02Bean?list[0].name=홍길동&list[1].name=이길동
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos : ", list);
		
		return "ex02Bean";
	}
	
	@GetMapping("/ex04") // @ModelAttribute : 화면에서 전달된 매개변수를 컨트롤러를 경유해서
	                     // View 페이지로 전달시 사용
						 // Model 바인딩이 되어서 전달되는 것
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) { // http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
		log.info("list dtos : ", dto);
		log.info("list dtos : ", page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() { //http://localhost:8080/sample/ex06
		log.info("/ex06");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		return dto;
	}
	// 
	// {"name":"홍길동"}
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07");
		
		String msg= "{\"name\":\"홍길동\"}";
		
		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		header.add("Content-type", "application/json;charset=utf-8");
		return new ResponseEntity<String>(msg,header,HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload............");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		// http로 전송된 파일을 로컬에 저장하는 처리를 해야함
		// inputStream, outputStream을 사용해서 로컬에 file로 저장하는 처리가 필요
		// 따라서, 실제 구현은 뒤에서 두룰 예정임
		
		// servlet-context.xml에 설정한 업로드 제한 속성을 기준으로
		// 벗어나는 파일일 경우 exception이 발생하게 되어 있음
		files.forEach(file->{
			log.info(".........................");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
		});
	}
	
	
}
