package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardTestDAO {
	private DataSource dataFactory; // DB 접속 정보를 가지고 있음(Context.xml)
	private Connection conn;
	private PreparedStatement pstmt;
	
	public BoardTestDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardTestVO> boardList(){
		List<BoardTestVO> boardList = new ArrayList();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_board order by writeDate desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int articleNo = rs.getInt("articleNo");
				int parentNo = rs.getInt("parentNo");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date writeDate = rs.getDate("writeDate");
				String id = rs.getString("id");
				BoardTestVO boardtestvo = new BoardTestVO(articleNo,parentNo,title,content,writeDate,id);
				boardList.add(boardtestvo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardList;
	}
	
}
