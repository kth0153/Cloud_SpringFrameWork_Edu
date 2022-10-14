package org.zerock.domain;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
	 
	//  /sample/ex03?title=testTitle&dueDate=2022/10/13
	// @InitBinder 대신 사용
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
}
