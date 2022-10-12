package sec00.ex01;

import java.sql.Date;

public class BoardVO {
	// 게시글에 필요한 value 값 담고 있는 곳
	private int articleNo;
	private String title;
	private String content;
	private Date writeDate;
	
	public BoardVO() {
		
	}
	
	public BoardVO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public BoardVO(int articleNo, String title, String content, Date writeDate) {
		super();
		this.articleNo = articleNo;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	
	
	
	
	
}
