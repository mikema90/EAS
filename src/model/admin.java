package model;

public class admin {
	private int id;
	private int work_id;
	private String name;
	private int pwd;
	private boolean opendeclare;
	private boolean openreview;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWork_id() {
		return work_id;
	}

	public void setWork_id(int work_id) {
		this.work_id = work_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPwd() {
		return pwd;
	}

	public void setPwd(int pwd) {
		this.pwd = pwd;
	}

	public boolean isOpendeclare() {
		return opendeclare;
	}

	public void setOpendeclare(boolean opendeclare) {
		this.opendeclare = opendeclare;
	}

	public boolean isOpenreview() {
		return openreview;
	}

	public void setOpenreview(boolean openreview) {
		this.openreview = openreview;
	}

}
