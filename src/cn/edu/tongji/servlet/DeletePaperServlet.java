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
import cn.edu.tongji.util.FileUtil;
import cn.edu.tongji.util.HibernateUtil;

@WebServlet("/deletePaper")
@SuppressWarnings("serial")
public class DeletePaperServlet extends HttpServlet {

	public DeletePaperServlet() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// cover Chinese character
		CommonFuncInServlet.setCharacterEncoding(request, response);

		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();

		boolean declareStatus = HibernateUtil.isOpenDeclare();
		if (declareStatus) {
			int paper_id = Integer.valueOf(request.getParameter("paper_id"));
			paper p = HibernateUtil.getOnePaperCount(paper_id);
			int upstatus = HibernateUtil.deletePaper(paper_id);

			// delete pdf file
			String filePath = request.getRealPath("/") + p.getPdf_url();
			System.out.println("delete file:" + filePath);
			FileUtil.deleteFile(filePath);

			if (upstatus == 1) {
				result.accumulate("Status", "success");
			}
		} else {
			result.accumulate("Status", "failed");
		}

		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
