package model;

public class reviewschedule {
	private int id;
	private int paper_id;
	private String expert_work_id;
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

	public String getExpert_work_id() {
		return expert_work_id;
	}

	public void setExpert_work_id(String expert_work_id) {
		this.expert_work_id = expert_work_id;
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
