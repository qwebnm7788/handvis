package basic;

import java.sql.Date;

public class Notification {
	private int idx;
	private String title;
	private String content;
	private Date datetime;
	
	
	public Notification() {
		// TODO Auto-generated constructor stub
	}
	
	public Notification(int idx, String title, String content, Date datetime) {
		super();
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.datetime = datetime;
	}
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
}

