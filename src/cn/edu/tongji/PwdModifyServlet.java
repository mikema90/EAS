package cn.edu.tongji;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.paper;

@WebServlet("/pwdmodify")
@SuppressWarnings("serial")
public class PwdModifyServlet extends HttpServlet {

	public PwdModifyServlet() {
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
		HttpSession session = request.getSession();
		// get current user profile
		String identity = (String) session.getAttribute("identity");
		String username = (String) session.getAttribute("username");
		String cur_pwd = (String) session.getAttribute("pwd");

		String old_pwd = request.getParameter("old pwd");
		String new_pwd = request.getParameter("new pwd");

		if (cur_pwd.equals(old_pwd)) {// old password equal login password
			HibernateUtil.pwdModify(identity, username, cur_pwd, new_pwd);
			String result = "{\"Status\":\"success\"}";
			System.out.println(result);
			out.write(result);
			out.flush();
			out.close();
		}

	}
}
