package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("select sysdate from dual")
	public String getTime1(); // Interface 사용
	
	public String getTime2(); // xml 사용

}
