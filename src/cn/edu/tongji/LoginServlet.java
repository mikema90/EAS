package cn.edu.tongji;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String seller_id = request.getParameter("seller_id");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");

		// set result and forward to output page
		request.setAttribute("key", seller_id + start_date + end_date);
		request.getRequestDispatcher("output.jsp").forward(request, response);
	}
}
