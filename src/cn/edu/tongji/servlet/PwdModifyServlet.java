package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;

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
		CommonFuncInServlet.setCharacterEncoding(request, response);

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// get current user profile
		String identity = (String) session.getAttribute("identity");
		String username = (String) session.getAttribute("username");
		String cur_pwd = (String) session.getAttribute("pwd");

		String old_pwd = request.getParameter("formerPwd");
		String new_pwd = request.getParameter("newPwd");

		JSONObject result = new JSONObject();
		if (cur_pwd.equals(old_pwd) && identity != null && username != null) {// old password equal login password
			HibernateUtil.pwdModify(identity, username, cur_pwd, new_pwd);
			result.accumulate("Status", "success");
			result.accumulate("retMsg", "密码修改成功，请重新登录");
			session.invalidate();
			System.out.println(result);
		} else {
			result.accumulate("Status", "error");
			result.accumulate("retMsg", "原密码输入错误，请重新输入");
		}
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
