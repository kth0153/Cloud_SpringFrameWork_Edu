package org.zerock.domain;

import java.sql.Date;

import lombok.Data;

@Data // getter/setter, 생성자, toString() 자동 생성
public class BoardVO {
	private Long bno; // sequence로 부터 번호 생성, sequence.nextval()
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	private int replyCnt;
	
}
