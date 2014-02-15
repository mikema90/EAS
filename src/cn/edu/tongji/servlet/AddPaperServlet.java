package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;
import net.sf.json.JSONObject;
import model.paper;

@WebServlet("/addPaper")
@SuppressWarnings("serial")
public class AddPaperServlet extends HttpServlet {

	public AddPaperServlet() {
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

		paper p = CommonFuncInServlet.fillinPaper(request);
		HibernateUtil.addPaper(p);

		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");
		result.accumulate("redirectUrl", "thesisList.html");

		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
