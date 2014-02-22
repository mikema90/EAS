package cn.edu.tongji.util;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.expert;
import model.paper;

/**
 * 
 * @author MIKE
 * 
 *         include all common function in servlet
 */
public class CommonFuncInServlet {

	public CommonFuncInServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * cover Chinese character
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void setCharacterEncoding(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	}

	@SuppressWarnings("deprecation")
	public static paper fillinPaper(HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession();
		String college_id = (String) session.getAttribute("username");
		String category = request.getParameter("thesisType"), title = request
				.getParameter("thesisName"), journal = request
				.getParameter("periodicalName"), issues = request
				.getParameter("periodicalType"), year = request
				.getParameter("timeYear"), month = request
				.getParameter("timeMonth"), language = request
				.getParameter("thesisLanguage"), fileTmpName = request
				.getParameter("fileTempName");
		String[] authorNames = request.getParameterValues("authorName"), authorIds = request
				.getParameterValues("authorId"), journalSN = request
				.getParameterValues("periodicalSn1");

		Date post_date = new Date(Integer.valueOf(year) - 1900,
				Integer.valueOf(month) - 1, 1);
		boolean passed = false;

		// for testing --delete later
		college_id = "8800";

		String rootPath = request.getRealPath("/");
		String tmpPath = rootPath + "tempUploadedFile" + File.separator
				+ fileTmpName;
		String pdf_url = college_id + File.separator + title + ".pdf";

		// move file to unified dir
		FileUtil.copyFile(tmpPath, rootPath + pdf_url);

		paper p = new paper();
		// fill data into paper
		p.setCollege_id(Integer.valueOf(college_id));
		p.setCollege_name(nameMapping.getInstance().collegeMap
				.get(college_id));
		p.setCategory(nameMapping.getInstance().categoryMap.get(category));

		String first_author = "", other_authors = "", other_authors_wid = "";
		int first_author_wid = -1;
		for (int i = 0; i < authorNames.length; i++) {
			if (i == 0) { // first_author
				first_author = authorNames[i];
				first_author_wid = Integer.valueOf(authorIds[i]);
			} else { // other authors
				other_authors = other_authors + authorNames[i] + ",";
				other_authors_wid = other_authors_wid + authorIds[i] + ",";
			}
		}

		p.setFirst_author(first_author);
		p.setFirst_author_wid(first_author_wid);
		p.setOther_authors(other_authors);
		p.setOther_authors_wid(other_authors_wid);
		p.setTitle(title);
		p.setJournal(journal);

		issues = issues + "-" + journalSN[0];
		p.setIssues(issues);

		p.setJournal_type(journalSN[1]);
		p.setPost_date(post_date);
		p.setLanguage(nameMapping.getInstance().languageMap.get(language));
		p.setPdf_url(pdf_url);
		p.setPassed(passed);

		return p;
	}

	public static expert fillinExpert(HttpServletRequest request) {
		expert e = new expert();
		// String work_id, college_id, name, pwd;

		// e.setWork_id(work_id);
		return e;
	}
}
