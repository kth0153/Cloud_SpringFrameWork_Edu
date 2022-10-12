package sec00.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

// 톰켓에 설정되는 것
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 모든 동작은 Controller에서 시작 된다.

/*
 * 1. 조회, 등록의 기능을 구현할 경우, 요청을 컨트롤러에서 처리해야 함.
		- 조회, 등록에 대한 요청을 구분할 수 있도록 해야 함
		
		request.getPathInfo();
		
	2. 신규 글 등록을 할 경우의 고려 사항
		- DAO에서 데이터베이스에 INSERT 할 경우, 글번호를 증가해서 등록해야함
		- insertBoard() 메소드 호출 전에 글번호를 조회해야 함.
		  selectMaxBoardNo()
*/


@WebServlet("*.do") // HttpServlet을 상속 받고 매핑이 제대로 되어야 @WebServlet이 제대로 동작됨
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	BoardService boardService; // 비지니스 로직 수행을 위해 BoardService 초기화(DB)
	
	public void init() throws ServletException {
		boardService = new BoardService(); // BoardService 생성자 호출 및 초기화
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardController");
		// doGet, doPost 함께 사용 하기 위해 doHandle() 메서드 설정
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// command 패턴
		// 1. request를 구분해 처리 해야함
		String nextPage = "";
//		String action = request.getPathInfo();
//		System.out.println("action:" + action);
		
		String action = request.getServletPath();
		System.out.println("command : " + action);
		
		if(action.equals("/board.do")) {
			// 전체글 조회
			nextPage = "/test00/boardList.jsp";
			
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			boardList = boardService.listBoardList(); // BoardService에서 받아온 값을 List에 저장	
			request.setAttribute("boardList", boardList); // DAO의 값을 JSP에 보내기 위해 저장
			
		}
		else if(action.equals("/addForm.do")) {
			// 등록 화면
			nextPage = "/test00/boardForm.jsp";
		}
		else if(action.equals("/addBoard.do")) {
			// 데이터 베이스에 등록 처리
			
			// 바인딩된 매개변수를 VO에 저장
			// SimpleDateFormat transForamat = new SimpleDateFormat("yyyy-MM-dd");
			// int articleNo = Integer.parseInt(request.getParameter("articleNo"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			System.out.println(title);
			System.out.println(content);
			
			// Date writeDate =  request.getParameter("writeDate");
			BoardVO board = new BoardVO(title,content);
			
			// 서비스
			boardService.addBoard(board);
			
			// 조회하는 화면으로 포워딩 되도록 페이지 설정
			nextPage = "/board.do";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); // RequestDispatcher를 이용해서 boardList를 view(jsp)로 이동
		dispatch.forward(request, response); // forward를 통해 dispatch에서 요청한 경로로 이동
	}
}
