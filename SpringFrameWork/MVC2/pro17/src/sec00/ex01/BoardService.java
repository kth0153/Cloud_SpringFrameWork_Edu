package sec00.ex01;

import java.util.List;

public class BoardService {
	// DB ���� => DAO 
	BoardDAO boardDAO;
	
	public BoardService() { // BoardService �⺻ ������
		boardDAO = new BoardDAO(); // BoardDAO ��ü ����
	}
		
	// ��ȸ�ϴ� ���� �޼���
	public List<BoardVO> listBoardList(){
		List<BoardVO> boardList = boardDAO.selectAllBoard(); // ����� list �޼��� ȣ��
		return boardList;
	}
	
	// �� ��� ���� �޼ҵ�
	public void addBoard(BoardVO board) {
		int result = boardDAO.insertNewBoard(board);
		
		if(result>0) {
			System.out.println("���� ����");
		}else {
			System.out.println("���� ����");
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
//			System.out.println("���� ����");
//		}else {
//			System.out.println("���� ����");
//		}
//	}
	
}
