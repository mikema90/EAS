package cn.edu.tongji;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import model.paper;

@WebServlet("/addPaper")
@SuppressWarnings("serial")
public class AddPaperServlet extends HttpServlet {

	public AddPaperServlet() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// cover Chinese character
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		paper p = fillinPaper(request);
		HibernateUtil.addPaper(p);

		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");
		result.accumulate("redirectUrl", "thesisList.html");

		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();

	}

	@SuppressWarnings("deprecation")
	public paper fillinPaper(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		String college_id = (String) session.getAttribute("username");
		String college_name = request.getParameter("school"), category = request
				.getParameter("thesisType"), title = request
				.getParameter("thesisName"), journal = request
				.getParameter("periodicalName"), issues = request
				.getParameter("periodicalType"), year = request
				.getParameter("timeYear"), month = request
				.getParameter("timeMonth"), language = request
				.getParameter("thesisLanguage"), fileTmpName = request
				.getParameter("fileTempName");
		String[] authorNames = request.getParameterValues("authorName"), journalSN = request
				.getParameterValues("periodicalSn1");

		Date post_date = new Date(Integer.valueOf(year) - 1900,
				Integer.valueOf(month) - 1, 1);
		boolean passed = false;

		// for testing --delete later
		college_id = "0088";

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
				.get(college_name));
		p.setCategory(nameMapping.getInstance().categoryMap.get(category));

		String first_author = "", other_authors = "";
		for (int i = 0; i < authorNames.length; i++) {
			if (i == 0) { // first_author
				first_author = authorNames[i];
			} else { // other authors
				other_authors = other_authors + authorNames[i] + ",";
			}
		}

		p.setFirst_author(first_author);
		p.setOther_authors(other_authors);
		p.setTitle(title);
		p.setJournal(journal);
		
		issues = issues + journalSN[0] + "-" + journalSN[1];
		p.setIssues(issues);
		
		p.setJournal_type(journalSN[1]);
		p.setPost_date(post_date);
		p.setLanguage(nameMapping.getInstance().languageMap.get(language));
		p.setPdf_url(pdf_url);
		p.setPassed(passed);

		return p;
	}
}
