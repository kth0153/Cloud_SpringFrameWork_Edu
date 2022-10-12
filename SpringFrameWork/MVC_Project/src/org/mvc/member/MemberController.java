package org.mvc.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberService memberService;

	public void init() throws ServletException {
		memberService = new MemberService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// Dao 처리 후 컨트롤러에서 이동할 최종 페이지
		String nextPage = null;
		
		// 사용자의 요청 정보 저장(조회, 수정 삭제, 등록)
		String action = request.getPathInfo();
		
		System.out.println("action : " + action);
		
		// http://localhost:8090/pro17/member 로 요청된 경우(null 인경우)
		// http://localhost:8090/pro17/member/ => null이 아님
		if(action == null || action.equals("/listMembers.do")) { 
			// 등록된 전체 회원 리스트 조회
			List<MemberVO> memberList = memberService.listMembers();
			// view로 데이터 전달하기 위해 request 객체에 데이터 바인딩
			request.setAttribute("membersList", memberList); // 변수명, 받아오는 멤버 변수명
			// 이동할 view page 설정
			nextPage = "/memberView/listMembers.jsp";
			
		}else if(action.equals("/memberForm.do")) {
			nextPage = "/memberView/memberForm.jsp";
			
		}else if(action.equals("/addMember.do")) {
			// memberForm.jsp에 입력된 회원 정보 VO에 저장
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			// VO를 DB에 저장하기 위해서 DAO에 전달
			MemberVO membervo = new MemberVO(id,pwd,name,email);
			memberService.addMember(membervo);
			
			// View 설정
			// - 이동할 페이지 정보 설정
			// - 사용자 화면에 결과를 알림 태그 정보 설정
			nextPage = "/member/listMembers.do";
			request.setAttribute("msg", "addMember"); // listMember.jsp에 msg 값 전달
			
		}else if(action.equals("/modMemberForm.do")) {
			// id -> controller -> 수정화면 -> controller -> DAO
			String id = request.getParameter("id");
			
			// id에 해당하는 데이터를 조회
			MemberVO memInfo = memberService.findMember(id);
			
			// 조회된 데이터를 바인딩
			request.setAttribute("memInfo", memInfo);
			
			// 수정화면으로 포워딩 페이지 설정			nextPage = "/memberView/modMemberForm.jsp";
			
		}else if(action.equals("/modMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			// VO를 DB에 저장하기 위해서 DAO에 전달
			MemberVO membervo = new MemberVO(id,pwd,name,email);
			memberService.modMember(membervo);
			
			request.setAttribute("msg", "modified");
			nextPage = "/member/listMembers.do";
			
		}else if(action.equals("/delMember.do")) {
			
			// 회원 삭제 처리
			// 해당 처리가 없으면, else 처리 => 회원리스트 화면으로
			
			// 행락(raw lock)
			// 1. sql developer 에서 한 건에 대해 update
			//	  commit 하면 안됨
			// 2. 회원관리 웹 애플리케이션에서
			//    1번에서 수정하려는 데이터를 삭제
			
			String id = request.getParameter("id");
			memberService.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage = "/member/listMembers.do";
		}else {
			
		}
		
		// 해당 JSP 페이지 경로로 포워딩
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	}

}
 