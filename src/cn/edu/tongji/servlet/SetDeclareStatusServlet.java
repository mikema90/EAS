package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;

@WebServlet("/setDeclareStatus")
@SuppressWarnings("serial")
public class SetDeclareStatusServlet extends HttpServlet {

	public SetDeclareStatusServlet() {
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

		String status = request.getParameter("DeclareStatus");
		int upstatus = 0;

		if (status.equals("open")) {
			upstatus = HibernateUtil.setDeclareStatus(true);
		} else if (status.equals("close")) {
			upstatus = HibernateUtil.setDeclareStatus(false);
		}

		JSONObject result = new JSONObject();
		if (upstatus == 1) {
			result.accumulate("Status", "success");
		} else {
			result.accumulate("Status", "failed");
		}

		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
