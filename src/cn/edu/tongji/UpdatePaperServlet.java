package cn.edu.tongji;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.paper;

@WebServlet("/updatePaper")
@SuppressWarnings("serial")
public class UpdatePaperServlet extends HttpServlet {

	public UpdatePaperServlet() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// cover Chinese character
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		String college, category, authors, title, journal, ISSN, ISBN, journal_type, language;
		String teacher_work_id = (String) request.getAttribute("username");
		Date post_date;
		boolean passed = false;

		String rootPath = request.getRealPath("/");
		String pdf_url = rootPath + teacher_work_id;
		paper p = new paper();
		// fill data into paper
		/*p.setTeacher_work_id(teacher_work_id);
		p.setCollege(college);
		p.setCategory(category);
		p.setAuthors(authors);
		p.setTitle(title);
		p.setJournal(journal);
		p.setIssn(ISSN);
		p.setIsbn(ISBN);
		p.setJournal_type(journal_type);
		p.setPost_date(post_date);
		p.setLanguage(language);
		p.setPdf_url(pdf_url);
		p.setPassed(passed);*/
		// -------------------------------------------------------------
		// p.setTeacher_work_id(teacher_work_id);
		HibernateUtil.addPaper(p);

		String result = "{\"Status\":\"success\"}";
		System.out.println(result);
		out.write(result);
		out.flush();
		out.close();

	}
}
