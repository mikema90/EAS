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
		if (identity.equals("expert")) {// only for expert
			
			
			
			JSONObject result = new JSONObject();
			result.accumulate("Status", "success");
			// ----------------------------------------------
			System.out.println(result.toString());
			out.write(result.toString());
			out.flush();
		}
		out.close();
	}
}
