package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.college;
import model.paper;
import net.sf.json.JSONObject;
import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;

@WebServlet("/getcollege")
@SuppressWarnings("serial")
public class GetCollegeServlet extends HttpServlet {

	public GetCollegeServlet() {
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

		List<college> colleges = HibernateUtil.getCollege();

		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");
		result.accumulate("college", colleges);
		
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}

}
