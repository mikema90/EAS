package model;

public class admin {
	private int id;
	private String work_id;
	private String name;
	private String pwd;
	private boolean opendeclare;
	private boolean openreview;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
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
