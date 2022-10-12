package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
	
	// 객체간의 의존 관계설정 -> 생성자 또는 setter 이용
	@Setter(onMethod_ = @Autowired) // chef 클래스 의존 주입(DI)
	private Chef chef;
}
