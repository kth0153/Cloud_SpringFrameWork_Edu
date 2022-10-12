package sec01.ex01;

import java.sql.Date;

public class BoardTestVO {
	private int articleNo;
	private int parentNo;
	private String title;
	private String content;
	private Date writeDate;
	private String id;
	
	public BoardTestVO() {
		System.out.println("BoardTestVO 생성자 호출");
	}
	
	public BoardTestVO(int articleNo, int parentNo, String title, String content, String id) {
		super();
		this.articleNo = articleNo;
		this.parentNo = parentNo;
		this.title = title;
		this.content = content;
		this.id = id;
	}
	
	public BoardTestVO(int articleNo, int parentNo, String title, String content, Date writeDate, String id) {
		super();
		this.articleNo = articleNo;
		this.parentNo = parentNo;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
		this.id = id;
	}

	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
	
}
