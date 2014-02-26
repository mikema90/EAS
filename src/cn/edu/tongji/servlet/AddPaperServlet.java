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
import cn.edu.tongji.util.paperFillException;
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

		paper p = null;
		boolean isSuccess = true;
		try {
			p = CommonFuncInServlet.fillinPaper(request);
		} catch (paperFillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess = false;
		}
		
		
		JSONObject result = new JSONObject();
		if(isSuccess){
			HibernateUtil.addPaper(p);
			result.accumulate("Status", "success");
			result.accumulate("redirectUrl", "thesisList.html");
		}else{
			result.accumulate("Status", "failed");
			result.accumulate("retMsg", "期刊名称和刊号不匹配！");
		}
		
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
