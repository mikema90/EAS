package model;

public class expert {
	private int id;
	private int work_id;
	private String name;
	private int pwd;
	private boolean engineering;
	private boolean science;
	private boolean medicine;
	private boolean arts;
	private boolean english;
	private boolean deutsch;
	private boolean japanese;
	private boolean other;

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

	public boolean isEngineering() {
		return engineering;
	}

	public void setEngineering(boolean engineering) {
		this.engineering = engineering;
	}

	public boolean isScience() {
		return science;
	}

	public void setScience(boolean science) {
		this.science = science;
	}

	public boolean isMedicine() {
		return medicine;
	}

	public void setMedicine(boolean medicine) {
		this.medicine = medicine;
	}

	public boolean isArts() {
		return arts;
	}

	public void setArts(boolean arts) {
		this.arts = arts;
	}

	public boolean isEnglish() {
		return english;
	}

	public void setEnglish(boolean english) {
		this.english = english;
	}

	public boolean isDeutsch() {
		return deutsch;
	}

	public void setDeutsch(boolean deutsch) {
		this.deutsch = deutsch;
	}

	public boolean isJapanese() {
		return japanese;
	}

	public void setJapanese(boolean japanese) {
		this.japanese = japanese;
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}
}
