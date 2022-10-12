package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class TimeMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private TimeMapper timeMapper;
	
	// Interface 활용
	@Test
	public void testGetTime1() {
		log.info("getTime1");
		log.info(timeMapper.getClass().getName()); // 실제 동작하는 클래스의 이름을 확인
		log.info(timeMapper.getTime1());
	}
	
	// xml 활용
	@Test
	public void testGetTime2() {
		log.info("getTime2");
		log.info(timeMapper.getClass().getName()); // 실제 동작하는 클래스의 이름을 확인
		log.info(timeMapper.getTime2());
		
	}
}
