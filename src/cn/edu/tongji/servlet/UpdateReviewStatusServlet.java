package cn.edu.tongji.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.tongji.util.CommonFuncInServlet;

@SuppressWarnings("serial")
public class UpdateReviewStatusServlet extends HttpServlet {

	public UpdateReviewStatusServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// cover Chinese character
		CommonFuncInServlet.setCharacterEncoding(request, response);

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String paper_id, expert_work_id,status,comment;
		//status 1-属于 2-不属于 3-无法认定
		//HibernateUtil.updateReviewStatus(paper_id, expert_work_id, status, comment);
		
		String result = "{\"Status\":\"success\"}";
		System.out.println(result);
		out.write(result);
		out.flush();
		out.close();
	}
}
