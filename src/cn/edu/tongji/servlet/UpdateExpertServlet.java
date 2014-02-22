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
import cn.edu.tongji.util.HibernateUtil;

@WebServlet("/updexpert")
@SuppressWarnings("serial")
public class UpdateExpertServlet extends HttpServlet {

	public UpdateExpertServlet() {
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

		// get id from url
		// int id = 0;// request.getRequestURI();
		// paper p = CommonFuncInServlet.fillinPaper(request);
		// p.setId(id);
		// HibernateUtil.updatePaper(p);

		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");

		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
