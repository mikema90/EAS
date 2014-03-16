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
		HttpSession session = request.getSession();

		int pageOffset = Integer.valueOf(request.getParameter("pageOffset"));
		int maxItemCount = Integer
				.valueOf(request.getParameter("maxItemCount"));
		// set default value if null
		if (pageOffset <= 0) {
			pageOffset = 1;
		}
		if (maxItemCount <= 0) {
			maxItemCount = 25;
		}

		String identity = (String) session.getAttribute("identity");
		String college_id = (String) session.getAttribute("username");
		int icount = 0;

		if ("college".equalsIgnoreCase(identity + "")) {
			icount = HibernateUtil.getPaperCount(college_id);
		} else if ("admin".equalsIgnoreCase(identity + "")) {
			icount = HibernateUtil.getAllPaperCount();
		}

		int pageCount = icount / maxItemCount;
		if (icount % maxItemCount != 0) {
			pageCount++;
		}

		if (pageOffset > pageCount) {// over max page
			pageOffset = pageCount;
		}
		int startIndex = (pageOffset - 1) * maxItemCount;

		List<paper> papers = null;
		if ("college".equalsIgnoreCase(identity + "")) {
			papers = HibernateUtil.getPaper(startIndex, maxItemCount,
					college_id);
		} else if ("admin".equalsIgnoreCase(identity + "")) {
			papers = HibernateUtil.getAllPaper(startIndex, maxItemCount);
		}

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
