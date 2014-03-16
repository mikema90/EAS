package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.EvaluationPaper;
import cn.edu.tongji.util.HibernateUtil;
import cn.edu.tongji.util.JsonDateValueProcessor;

@WebServlet("/getEvaluationPaper")
// return evaluation paper for one specific expert
@SuppressWarnings("serial")
public class GetEvaluationPaper extends HttpServlet {

	public GetEvaluationPaper() {
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
		String username = (String) session.getAttribute("username");
		if (identity.equals("expert")) {// only for expert

			List<EvaluationPaper> evaluatonpapers = HibernateUtil
					.getEvaluationPaper(username);

			// json config
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			// json to return
			JSONObject result = new JSONObject();
			JSONArray jaPapers = JSONArray.fromObject(evaluatonpapers,
					jsonConfig);
			result.accumulate("paper", jaPapers);
			result.accumulate("Status", "success");
			// ----------------------------------------------
			System.out.println(result.toString());
			out.write(result.toString());
			out.flush();
		}
		out.close();
	}
}
