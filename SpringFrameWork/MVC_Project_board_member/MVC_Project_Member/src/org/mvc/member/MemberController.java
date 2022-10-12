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
	MemberDAO memberDAO;

	public void init() throws ServletException {
		memberDAO = new MemberDAO();
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
		//Dao 처리 후 컨트롤러에서 이동할 최종 페이지
		String nextPage = null;
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//사용자의 요청 정보 저장.(조회, 수정, 삭제, 등록)
		String action = request.getPathInfo();
		
		System.out.println("action : " + action);
		
		//http://localhost:8090/pro17/member 로 요청된 경우.
		if (action == null || action.equals("/listMembers.do")) { 
			// 등록된 전체 회원 리스트 조회
			List<MemberVO> memberList = memberDAO.listMembers();
			//view 로 데이터 전달하기 위해 request 객체에 데이터 바인딩.
			request.setAttribute("membersList", memberList);
			//이동할 view page 설정
			nextPage = "/memberView/listMembers.jsp";
		} else if (action.equals("/memberForm.do")) {
			nextPage = "/memberView/memberForm.jsp";
		} else if (action.equals("/addMember.do")) {
			// 입력된 회원 정보 VO에 저장
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			
			// VO를 DB 저장하기 위해서 DAO 에 전달
			memberDAO.addMember(memberVO);
			
			// View 설정
			// - 이동할 페이지 정보 설정 
			// - 사용자 화면에 결과를 알림 태그 정보 설정
			nextPage = "/member/listMembers.do";
			request.setAttribute("msg", "addMember");
		}
		
		//JSP View 페이지로 포워딩.
		RequestDispatcher dispatch = 
				request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
