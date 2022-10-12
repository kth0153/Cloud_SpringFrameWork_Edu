package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component // 스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시 어노테이션
@Data // Lombok의 setter, 생성자, toString() 메소드 자동 생성, 컴파일시 setter 생성자 등을 만들어줌
public class Chef {
	public Chef() {
		
	}
}
