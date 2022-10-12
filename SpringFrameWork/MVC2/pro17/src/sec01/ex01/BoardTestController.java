package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardtest.do")
public class BoardTestController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		BoardTestDAO boardTestDAO;
		
	    public BoardTestController() {
	        super();
	        
	    }

		public void init() throws ServletException {
			boardTestDAO = new BoardTestDAO();
		}


		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doHandle(request, response);
		}


		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doHandle(request, response);
		}
		
		private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			List<BoardTestVO> boardList = boardTestDAO.boardList();
			System.out.println("do");
			request.setAttribute("boardList", boardList);
			RequestDispatcher dispatch = request.getRequestDispatcher("/test01/boardTestList.jsp"); // RequestDispatcher를 이용해서 memberList를 view(jsp)로 이동
			dispatch.forward(request, response);
		}
}