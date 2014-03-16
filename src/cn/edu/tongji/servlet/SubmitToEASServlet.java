package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.HibernateUtil;

@WebServlet("/submitToEAS")
@SuppressWarnings("serial")
public class SubmitToEASServlet extends HttpServlet {

	public SubmitToEASServlet() {
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
		String identity = (String) session.getAttribute("identity");
		String username = (String) session.getAttribute("username");
		
		JSONObject result = new JSONObject();
		
		if(identity == "college"){
			HibernateUtil.setCollegeSubmitted(true, username);
			result.accumulate("Status", "success");
		}else if(identity == "expert"){
			HibernateUtil.setExpertSubmitted(true, username);
			result.accumulate("Status", "success");
		}else{
			result.accumulate("Status", "failed");
		}
		
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
