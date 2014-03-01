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

@WebServlet("/getDeclareStatus")
@SuppressWarnings("serial")
public class GetDeclareStatusServlet extends HttpServlet {

	public GetDeclareStatusServlet() {
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

		boolean cur_status = HibernateUtil.isOpenDeclare();

		JSONObject result = new JSONObject();
		if (cur_status) {
			result.accumulate("submittingStatus", "open");
		} else {
			result.accumulate("submittingStatus", "close");
		}

		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
