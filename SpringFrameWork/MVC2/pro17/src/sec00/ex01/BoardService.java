package sec00.ex01;

import java.util.List;

public class BoardService {
	// DB 연동 => DAO 
	BoardDAO boardDAO;
	
	public BoardService() { // BoardService 기본 생성자
		boardDAO = new BoardDAO(); // BoardDAO 객체 생성
	}
		
	// 조회하는 서비스 메서드
	public List<BoardVO> listBoardList(){
		List<BoardVO> boardList = boardDAO.selectAllBoard(); // 출력할 list 메서드 호출
		return boardList;
	}
	
	// 글 등록 서비스 메소드
	public void addBoard(BoardVO board) {
		int result = boardDAO.insertNewBoard(board);
		
		if(result>0) {
			System.out.println("삽입 성공");
		}else {
			System.out.println("삽입 실패");
		}
	}
	
//	public void addBoard(String title, String content) {
//		BoardVO boardvo = new BoardVO();
//		boardvo.setTitle(title);
//		boardvo.setContent(content);
//		
//		int result = boardDAO.addboard(boardvo);
//		
//		if(result>0) {
//			System.out.println("삽입 성공");
//		}else {
//			System.out.println("삽입 실패");
//		}
//	}
	
}
