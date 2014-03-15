package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.paper;
import net.sf.json.JSONObject;
import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.FileUtil;
import cn.edu.tongji.util.HibernateUtil;

@WebServlet("/deleteExpert")
@SuppressWarnings("serial")
public class DeleteExpertServlet extends HttpServlet {
	public DeleteExpertServlet() {
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

		String work_id = request.getParameter("expertId");
		int upstatus = HibernateUtil.deleteExpert(work_id);

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
