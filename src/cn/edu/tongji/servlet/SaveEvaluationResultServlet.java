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
		String expert_work_id = (String) session.getAttribute("username");
		List<Integer> paper_ids = null;
		List<String> statuses = null;
		List<String> comments = null;
		if (paper_ids.size() == statuses.size()
				&& statuses.size() == comments.size()) {
			for (int i = 0; i < paper_ids.size(); i++) {
				int paper_id = paper_ids.get(i);
				String status = statuses.get(i), comment = comments.get(i);
				HibernateUtil.saveReviewStatus(paper_id, expert_work_id,
						status, comment);
			}
			JSONObject result = new JSONObject();
			result.accumulate("Status", "success");
			// -----------------------------------------------
			System.out.println(result.toString());
			out.write(result.toString());
			out.flush();
		}
		out.close();
	}
}
