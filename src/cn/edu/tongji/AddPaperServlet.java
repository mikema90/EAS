package cn.edu.tongji;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.paper;

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
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		String college, category, author, title, journal, ISSN, ISBN, journal_type, language, pdf_url;
		int teacher_work_id;
		Date post_date;
		boolean passed;

		paper p = new paper();
		// p.setTeacher_work_id(teacher_work_id);
		HibernateOperation ho = new HibernateOperation();
		ho.addPaper(p);

		String result = "{\"Status\":\"success\"}";
		System.out.println(result);
		out.write(result);
		out.flush();
		out.close();

	}
}
