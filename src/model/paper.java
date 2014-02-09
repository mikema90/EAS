package model;

import java.sql.Date;

public class paper {
	private int id;
	private int college_id;
	private String college_name;
	private String category;
	private String first_author;
	private String other_authors;
	private String title;
	private String journal;
	private String issues;
	private String journal_type;
	private Date post_date;
	private String language;
	private String pdf_url;
	private boolean passed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFirst_author() {
		return first_author;
	}

	public void setFirst_author(String first_author) {
		this.first_author = first_author;
	}

	public String getOther_authors() {
		return other_authors;
	}

	public void setOther_authors(String other_authors) {
		this.other_authors = other_authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getIssues() {
		return issues;
	}

	public void setIssues(String issues) {
		this.issues = issues;
	}

	public String getJournal_type() {
		return journal_type;
	}

	public void setJournal_type(String journal_type) {
		this.journal_type = journal_type;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPdf_url() {
		return pdf_url;
	}

	public void setPdf_url(String pdf_url) {
		this.pdf_url = pdf_url;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

}
