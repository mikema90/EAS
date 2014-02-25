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

@WebServlet("/mapping")
@SuppressWarnings("serial")
public class MappingServlet extends HttpServlet {

	public MappingServlet() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// cover Chinese character
		CommonFuncInServlet.setCharacterEncoding(request, response);

		PrintWriter out = response.getWriter();

		// get issues and journal, transfer them into UpperCase
		String issues = request.getParameter("issues").toUpperCase();
		String journal = request.getParameter("journal").toUpperCase();
		String journal_type = HibernateUtil.getMapping(issues, journal);

		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");
		if (journal_type != "" && journal_type != null) {
			result.accumulate("journal_type", journal_type);
		} else {
			result.accumulate("journal_type", "非核心期刊");
		}

		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
