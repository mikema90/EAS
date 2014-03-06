package model;

public class college {
	private int id;
	private String college_id;
	private String name;
	private String subject;
	private String pwd;
	private boolean submitted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollege_id() {
		return college_id;
	}

	public void setCollege_id(String college_id) {
		this.college_id = college_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
}
