package cn.edu.tongji.util;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
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
			throws IOException, typeMappingException {
		HttpSession session = request.getSession();
		String college_id = (String) session.getAttribute("username");
		String category = request.getParameter("thesisType"), title = request
				.getParameter("thesisName"), journal = request
				.getParameter("periodicalName"), year = request
				.getParameter("timeYear"), month = request
				.getParameter("timeMonth"), language = request
				.getParameter("thesisLanguage"), fileTmpName = request
				.getParameter("fileTempName");
		String[] authorNames = request.getParameterValues("authorName"), authorIds = request
				.getParameterValues("authorId");

		Date post_date = new Date(Integer.valueOf(year) - 1900,
				Integer.valueOf(month) - 1, 1);
		boolean passed = false;

		String pdf_url = "";
		if (fileTmpName == null || fileTmpName == "") {// not existing upload
														// file
			pdf_url = "";
		} else { // existing upload file
			String rootPath = request.getRealPath("/");
			String tmpPath = rootPath + "tempUploadedFile" + File.separator
					+ fileTmpName;
			System.out.println(tmpPath);
			pdf_url = college_id + File.separator + title + ".pdf";

			// move file to unified dir
			FileUtil.copyFile(tmpPath, rootPath + pdf_url);
		}

		paper p = new paper();
		// fill data into paper
		p.setCollege_id(college_id);
		p.setCollege_name(nameMapping.getInstance().collegeMap.get(college_id));
		p.setCategory(nameMapping.getInstance().categoryMap.get(category));

		String first_author = "", other_authors = "", other_authors_wid = "";
		String first_author_wid = "";
		for (int i = 0; i < authorNames.length; i++) {
			if (i == 0) { // first_author
				first_author = authorNames[i];
				first_author_wid = authorIds[i];
			} else { // other authors
				other_authors = other_authors + authorNames[i] + ",";
				other_authors_wid = other_authors_wid + authorIds[i] + ",";
			}
		}

		// if exist other authors then remove the last ","
		if (other_authors != "" && other_authors_wid != "") {
			other_authors = other_authors.substring(0,
					other_authors.length() - 1);
			other_authors_wid = other_authors_wid.substring(0,
					other_authors_wid.length() - 1);
		}

		p.setFirst_author(first_author);
		p.setFirst_author_wid(first_author_wid);
		p.setOther_authors(other_authors);
		p.setOther_authors_wid(other_authors_wid);
		p.setTitle(title);
		p.setJournal(journal);

		// get issues
		String issues = mergeIssues(request);
		p.setIssues(issues);

		String journal_type = HibernateUtil.getMapping(issues.toUpperCase(),
				journal.toUpperCase());
		if (journal_type.equals("#failed#")) {
			// 期刊名称和刊号不匹配
			throw new typeMappingException();
		}

		p.setJournal_type(journal_type);
		p.setPost_date(post_date);
		p.setLanguage(nameMapping.getInstance().languageMap.get(language));
		p.setPdf_url(pdf_url);
		p.setPassed(passed);

		return p;
	}

	public static String mergeIssues(HttpServletRequest request) {
		String issues = request.getParameter("periodicalType"), journalSN1 = request
				.getParameter("periodicalSn1"), journalSN2 = request
				.getParameter("periodicalSn2");
		if (issues.equals("ISSN")) {
			issues = issues + "-" + journalSN1 + "-" + journalSN2;
		} else if (issues.equals("ISBN")) {
			issues = issues + "-" + journalSN1;
		} else if (issues.equals("CN")) {
			issues = issues + "-" + journalSN1 + "-" + journalSN2;
		}

		return issues;
	}

	/**
	 * 
	 * @param journal_type
	 * @param flag
	 *            1 --- from mapping servlet 2 --- from addpaper servlet
	 * @return
	 */
	public static JSONObject handleJournalType(String journal_type, int flag) {
		JSONObject result = new JSONObject();
		if (flag == 1) {
			if (journal_type.equals("#failed#")) {
				result.accumulate("Status", "failed");
				result.accumulate("retMsg", "期刊名称与刊号不匹配!");
			} else {
				result.accumulate("Status", "success");
				result.accumulate("retMsg", journal_type);
			}
		} else if (flag == 2) {
			if (journal_type.equals("#failed#")) {
				result.accumulate("Status", "failed");
				result.accumulate("retMsg", "期刊名称与刊号不匹配！");
			} else if (journal_type.equals("#success#")) {
				result.accumulate("Status", "success");
				result.accumulate("redirectUrl", "thesisList.html");
			}
		}

		return result;
	}

	public static expert fillinExpert(HttpServletRequest request) {
		// medicalLife, engineering, science, otherMajor, college_id
		expert e = new expert();
		String college_id = request.getParameter("school"), name = request
				.getParameter("expertName"), work_id = request
				.getParameter("expertId");
		// pwd;

		// e.setWork_id(work_id);
		return e;
	}
}
