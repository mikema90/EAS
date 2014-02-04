package cn.edu.tongji;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

@WebServlet("/login")
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
		// cover Chinese character
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		String identity = request.getParameter("userType");
		String username = request.getParameter("userName");
		String pwd = request.getParameter("password");

		JSONObject result = new JSONObject();
		// set result and output
		if (HibernateUtil.isPwdValid(identity, username, pwd)) {
			// request.setAttribute("key", "You login successfully!");
			// request.getRequestDispatcher("output.jsp").forward(request,
			// response);

			HttpSession session = request.getSession();
			// set current user profile
			session.setAttribute("identity", identity);
			session.setAttribute("username", username);
			session.setAttribute("pwd", pwd);

			result.accumulate("Status", "success");
			result.accumulate("redirectUrl", "thesisList.html");
		} else {
			result.accumulate("Status", "failed");
		}
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}

}
