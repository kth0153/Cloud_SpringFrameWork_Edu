package sec00.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import sec01.ex01.BoardTestVO;

public class BoardDAO {
	// DB ������ �ʼ� ���
	private DataSource dataFactory; // DB ���� ������ ������ ����(Context.xml)
	private Connection conn;
	private PreparedStatement pstmt;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoardVO> selectAllBoard() {
		List<BoardVO> boardList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_board order by writeDate desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int articleNo = rs.getInt("articleNo");				
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date writeDate = rs.getDate("writeDate");
				BoardVO board = new BoardVO(articleNo,title,content,writeDate);
				
				boardList.add(board);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}
	
	// ��� �۹�ȣ ��ȸ(DAO �ȿ����� ��� �ϱ⿡ private ���)
	private int getNewBoardNo() {
		int articleNo =0;
		try {
			conn = dataFactory.getConnection();
			String query = "select NVL2(max(articleNo),max(articleNo)+1,1) anumn from t_board";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if(rs.next()) {
				articleNo = rs.getInt(1); // ������ ��� fidx �÷��� fgroup�� ��ȣ�� ����
			}
			System.out.println("articleNo �˻� :" + articleNo);
			rs.close();
			pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			return articleNo;
		}
		System.out.println("articleNo ���� :" + articleNo);
		return articleNo;
	}
	
	// �۵��
	public int insertNewBoard(BoardVO board) {
		int addchk=0;	
		try {
			conn = dataFactory.getConnection();
			//SimpleDateFormat transForamat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			int boardNo = getNewBoardNo(); // articleNo �� ������
			System.out.println("boardNo :" + boardNo);
			
			String title = board.getTitle();
			String content = board.getContent();
			
			String query = "insert into t_board(articleNo, title, content,writeDate) values(?,?,?,sysdate)";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			addchk = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			return addchk;
		}
		return addchk;
	
	}
	
	public int addboard(BoardVO m) {
		int addchk=0;
		try {
			conn = dataFactory.getConnection();
			String query = "select NVL2(max(articleNo),max(articleNo)+1,1) anum, sysdate ctime from t_board";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			rs.next();
			int articleNo = rs.getInt("anum"); // ������ ��� fidx �÷��� fgroup�� ��ȣ�� ����
			Date writeDate = rs.getDate("ctime");
			rs.close();
			pstmt.close();
			
			String title = m.getTitle();
			String content = m.getContent();
			System.out.println(articleNo);
			System.out.println(title);
			System.out.println(content);
			System.out.println(writeDate);
			
			query = "insert into t_board(articleNo, title, content,writeDate) values(?,?,?,sysdate)";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setDate(4, writeDate);
			
			addchk = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return addchk;
		}
		return addchk;
	}

}
