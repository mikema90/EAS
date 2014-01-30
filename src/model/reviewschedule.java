package model;

public class reviewschedule {
	private int id;
	private int paper_id;
	private int exper_work_id;
	private int status;
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(int paper_id) {
		this.paper_id = paper_id;
	}

	public int getExper_work_id() {
		return exper_work_id;
	}

	public void setExper_work_id(int exper_work_id) {
		this.exper_work_id = exper_work_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
