package sec00.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

// ���Ͽ� �����Ǵ� ��
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ��� ������ Controller���� ���� �ȴ�.

/*
 * 1. ��ȸ, ����� ����� ������ ���, ��û�� ��Ʈ�ѷ����� ó���ؾ� ��.
		- ��ȸ, ��Ͽ� ���� ��û�� ������ �� �ֵ��� �ؾ� ��
		
		request.getPathInfo();
		
	2. �ű� �� ����� �� ����� ��� ����
		- DAO���� �����ͺ��̽��� INSERT �� ���, �۹�ȣ�� �����ؼ� ����ؾ���
		- insertBoard() �޼ҵ� ȣ�� ���� �۹�ȣ�� ��ȸ�ؾ� ��.
		  selectMaxBoardNo()
*/


@WebServlet("*.do") // HttpServlet�� ��� �ް� ������ ����� �Ǿ�� @WebServlet�� ����� ���۵�
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	BoardService boardService; // �����Ͻ� ���� ������ ���� BoardService �ʱ�ȭ(DB)
	
	public void init() throws ServletException {
		boardService = new BoardService(); // BoardService ������ ȣ�� �� �ʱ�ȭ
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardController");
		// doGet, doPost �Բ� ��� �ϱ� ���� doHandle() �޼��� ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// command ����
		// 1. request�� ������ ó�� �ؾ���
		String nextPage = "";
//		String action = request.getPathInfo();
//		System.out.println("action:" + action);
		
		String action = request.getServletPath();
		System.out.println("command : " + action);
		
		if(action.equals("/board.do")) {
			// ��ü�� ��ȸ
			nextPage = "/test00/boardList.jsp";
			
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			boardList = boardService.listBoardList(); // BoardService���� �޾ƿ� ���� List�� ����	
			request.setAttribute("boardList", boardList); // DAO�� ���� JSP�� ������ ���� ����
			
		}
		else if(action.equals("/addForm.do")) {
			// ��� ȭ��
			nextPage = "/test00/boardForm.jsp";
		}
		else if(action.equals("/addBoard.do")) {
			// ������ ���̽��� ��� ó��
			
			// ���ε��� �Ű������� VO�� ����
			// SimpleDateFormat transForamat = new SimpleDateFormat("yyyy-MM-dd");
			// int articleNo = Integer.parseInt(request.getParameter("articleNo"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			System.out.println(title);
			System.out.println(content);
			
			// Date writeDate =  request.getParameter("writeDate");
			BoardVO board = new BoardVO(title,content);
			
			// ����
			boardService.addBoard(board);
			
			// ��ȸ�ϴ� ȭ������ ������ �ǵ��� ������ ����
			nextPage = "/board.do";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); // RequestDispatcher�� �̿��ؼ� boardList�� view(jsp)�� �̵�
		dispatch.forward(request, response); // forward�� ���� dispatch���� ��û�� ��η� �̵�
	}
}
