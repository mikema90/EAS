package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;
import model.paper;

@WebServlet("/updatePaper")
@SuppressWarnings("serial")
public class UpdatePaperServlet extends HttpServlet {

	public UpdatePaperServlet() {
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

		//get id from url
		int id = 0;//request.getRequestURI();
		paper p = CommonFuncInServlet.fillinPaper(request);
		p.setId(id);
		HibernateUtil.updatePaper(p);

		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");
		result.accumulate("redirectUrl", "thesisList.html");

		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
