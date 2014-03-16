package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.expert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;

@WebServlet("/getExpert")
@SuppressWarnings("serial")
public class GetExpertServlet extends HttpServlet {

	public GetExpertServlet() {
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
		String identity = (String) session.getAttribute("identity");

		JSONObject result = new JSONObject();
		if ("admin".equalsIgnoreCase(identity + "")) {
			List<expert> experts = HibernateUtil.getExpert();

			JSONArray jaExperts = JSONArray.fromObject(experts);
			result.accumulate("expert", jaExperts);
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
