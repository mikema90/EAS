package cn.edu.tongji;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String identity = request.getParameter("identity");
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String cer = request.getParameter("cer");

		HibernateOperation ho = new HibernateOperation();

		// set result and forward to output page
		if (ho.hasPermission(identity, username, pwd)) {
			request.setAttribute("key", "You login successfully!");
			request.getRequestDispatcher("output.jsp").forward(request,
					response);

			HttpSession session = request.getSession();
			// set current user profile
			session.setAttribute("username", username);
			session.setAttribute("pwd", pwd);
		} else {
			request.setAttribute("key", "You login failed!");
			request.getRequestDispatcher("output.jsp").forward(request,
					response);
		}

	}

}
