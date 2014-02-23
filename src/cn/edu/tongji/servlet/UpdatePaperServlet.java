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
import cn.edu.tongji.util.FileUtil;
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

		// get paper_id from request
		int paper_id = Integer.valueOf(request.getParameter("paper_id"));
		// from DB
		paper old_paper = HibernateUtil.getOnePaperCount(paper_id);
		// from html
		paper cur_paper = CommonFuncInServlet.fillinPaper(request);
		cur_paper.setId(paper_id);

		HibernateUtil.updatePaper(merge(old_paper, cur_paper, request));

		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");
		result.accumulate("redirectUrl", "thesisList.html");

		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}

	/**
	 * merge the info of old_paper and cur_paper
	 * 
	 * @param old_paper
	 * @param cur_paper
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public paper merge(paper old_paper, paper cur_paper,
			HttpServletRequest request) {
		if (cur_paper.getPdf_url() == "" || cur_paper.getPdf_url() == null) {// not
																				// upload
																				// file
			// using old file and just rename it
			String sourcePath = old_paper.getPdf_url();
			String newName = cur_paper.getTitle() + ".pdf";
			String targetPath = FileUtil.renameFile(sourcePath, newName);
			cur_paper.setPdf_url(targetPath);
		} else {// upload new file
				// not the same name, delete the old one
			if (!cur_paper.getTitle().equals(old_paper.getTitle())) {
				String filePath = request.getRealPath("/")
						+ old_paper.getPdf_url();
				FileUtil.deleteFile(filePath);
			}
		}
		return cur_paper;
	}
}
