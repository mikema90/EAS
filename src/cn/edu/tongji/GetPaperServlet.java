package cn.edu.tongji;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.paper;

@WebServlet("/getPaper")
@SuppressWarnings("serial")
public class GetPaperServlet extends HttpServlet {

	public GetPaperServlet() {
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

		List<paper> papers = HibernateUtil.getPaper();

		String result = "{\"Status\":\"success\", \"title\":\"Hibernate Optimization\"}";
		System.out.println(result);
		out.write(result);
		out.flush();
		out.close();
	}
}
