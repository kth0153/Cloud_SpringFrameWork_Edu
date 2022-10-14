package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice // 컨트롤러에서 발생하는 예외에 대해서 처리하는 객체
@Log4j2
public class CommonExceptionAdvice {
	@ExceptionHandler(Exception.class) // Exception.class예외에 대해서 처리함 모든 예외가 대상임
	public String except(Exception ex, Model model) {
		
		log.error("Exception.........."+ ex.getMessage());
		model.addAttribute("exception",ex); // 사용자에게 현재의 에러상황을 공유
		log.error(model);
		
		return "error_page"; // view 페이지 정보
		
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
		// NoHandlerFoundException 발생시 처리하는 메소드임
		// 따라서, 예외가 발생하면 예외 view 페이지로 포워딩되도록 해서
		// 사용자에게 현 예외 상황을 알리게 됨
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		return "custom404"; // 해당하는 jsp가 없는 경우는 모두 custom404.jsp로 포워딩
	}
}
