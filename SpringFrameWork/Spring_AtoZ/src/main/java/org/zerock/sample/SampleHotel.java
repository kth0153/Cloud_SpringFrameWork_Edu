package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
// @AllArgsConstructor // 생성자 생성
@RequiredArgsConstructor
public class SampleHotel {
	@NonNull // 여러개의 인스턴스 변수들 중 특정한 변수에 대해서만 생성자를 작성하기 위해 사용 @RequiredArgsConstructor를 사용해도 됨
	private Chef chef;

	private Chef chef2;
	
//	public SampleHotel(Chef chef) { //@AutoWired 생략
//		this.chef = chef;
//	}
}
