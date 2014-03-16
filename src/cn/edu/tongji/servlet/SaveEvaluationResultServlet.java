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

@WebServlet("/saveEvaluationResult")
@SuppressWarnings("serial")
public class SaveEvaluationResultServlet extends HttpServlet {

	public SaveEvaluationResultServlet() {
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
		JSONObject result = new JSONObject();

		String expert_work_id = (String) session.getAttribute("username");
		String[] paper_ids = request.getParameterValues("tupleId");
		String[] statuses = request.getParameterValues("evalResult");
		String[] comments = request.getParameterValues("remark");
		if (paper_ids.length == statuses.length
				&& statuses.length == comments.length) {
			for (int i = 0; i < paper_ids.length; i++) {
				int paper_id = Integer.valueOf(paper_ids[i]);
				String status = statuses[i], comment = comments[i];
				HibernateUtil.saveReviewStatus(paper_id, expert_work_id,
						status, comment);
			}
			result.accumulate("Status", "success");
		} else {
			result.accumulate("Status", "failed");
			System.out
					.println("lengths of tupleId, evalResult & remark are not equal!");
		}
		// -----------------------------------------------
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
