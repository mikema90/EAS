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
		JSONObject result = new JSONObject();

		HttpSession session = request.getSession();
		String identity = (String) session.getAttribute("identity");
		if (identity.equals("admin")) {
			String sub_status = request.getParameter("submittingStatus");
			int upstatus = 0;

			if (sub_status.equals("open")) {
				upstatus = HibernateUtil.setDeclareStatus(true);
			} else if (sub_status.equals("close")) {
				upstatus = HibernateUtil.setDeclareStatus(false);
			}

			if (upstatus == 1) {
				result.accumulate("Status", "success");
			} else {
				result.accumulate("Status", "failed");
			}

			System.out.println(result.toString());
			out.write(result.toString());
			out.flush();
			out.close();
		} else {
			result.accumulate("Status", "failed");
		}
	}
}
