package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;
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
		CommonFuncInServlet.setCharacterEncoding(request, response);

		PrintWriter out = response.getWriter();

		String identity = request.getParameter("userType");
		String username = request.getParameter("userName");
		String pwd = request.getParameter("password");

		JSONObject result = new JSONObject();
		// set result and output
		if (identity != "" && username != "" && pwd != ""
				&& HibernateUtil.isPwdValid(identity, username, pwd)) {
			HttpSession session = request.getSession();
			// set current user profile
			session.setAttribute("identity", identity);
			session.setAttribute("username", username);
			session.setAttribute("pwd", pwd);

			result.accumulate("Status", "success");

			if ("college".equalsIgnoreCase(identity + "")) {
				result.accumulate("redirectUrl", "thesisList.html");
			} else if ("admin".equalsIgnoreCase(identity + "")) {
				result.accumulate("redirectUrl", "manageThesisList.html");
			} else if ("expert".equalsIgnoreCase(identity + "")) {
				result.accumulate("redirectUrl", "expertNotice.html");
			} else {
				result.accumulate("redirectUrl", "about:blank");
			}
		} else {
			result.accumulate("Status", "failed");
		}
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}

}
