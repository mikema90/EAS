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

import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;
import cn.edu.tongji.util.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
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

		int pageOffset = Integer.valueOf(request.getParameter("pageOffset"));
		int maxItemCount = Integer
				.valueOf(request.getParameter("maxItemCount"));
		String college_id = "8800";

		int icount = HibernateUtil.getPaperCount(Integer.valueOf(college_id));
		int pageCount = (icount / maxItemCount) + 1;

		if (pageOffset > pageCount) {// over max page
			pageOffset = pageCount;
		}
		int startIndex = (pageOffset - 1) * maxItemCount;
		List<paper> papers = HibernateUtil.getPaper(startIndex, maxItemCount,
				Integer.valueOf(college_id));

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());

		// json to return
		JSONObject result = new JSONObject();
		JSONArray jaPapers = JSONArray.fromObject(papers, jsonConfig);
		result.accumulate("paper", jaPapers);
		result.accumulate("Status", "success");
		result.accumulate("pageOffset", pageOffset);
		result.accumulate("pageCount", pageCount);
		// ----------------------------------------------
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
