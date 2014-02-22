package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;
import net.sf.json.JSONObject;
import model.paper;

@WebServlet("/getPaper")
@SuppressWarnings("serial")
public class GetPaperServlet extends HttpServlet {

	public GetPaperServlet() {
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

		int pageroffset = 0;
		int maxcount = 1;
		String college_id = "8800";
		List<paper> papers = HibernateUtil.getPaper(pageroffset, maxcount, Integer.valueOf(college_id));

		// json to return
		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");
		result.accumulate("paper", papers);
		// ----------------------------------------------
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
