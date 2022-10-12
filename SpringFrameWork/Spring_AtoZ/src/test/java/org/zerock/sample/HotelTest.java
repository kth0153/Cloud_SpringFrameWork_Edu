package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class HotelTest {
	
	@Setter(onMethod_ = {@Autowired})
	private SampleHotel hotel;
	
	@Test
	public void testExists() {
		// hotel 인스턴스 변수가 null이 아니라면, 묵시적 의존주입이 된 것임
		assertNotNull(hotel);
		log.info(hotel);
		log.info("------------------------------");
		log.info(hotel.getChef());
		log.info(hotel.getChef2());
	}
	
}
