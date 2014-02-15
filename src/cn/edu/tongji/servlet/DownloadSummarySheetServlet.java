package cn.edu.tongji.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.tongji.util.CommonFuncInServlet;
import cn.edu.tongji.util.FileUtil;
import cn.edu.tongji.util.HibernateUtil;
import jxl.JXLException;
import model.paper;
import net.sf.json.JSONObject;

@WebServlet("/downloadSummarySheet")
@SuppressWarnings("serial")
public class DownloadSummarySheetServlet extends HttpServlet {

	public DownloadSummarySheetServlet() {
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

		HttpSession session = request.getSession();
		String college_id = (String) session.getAttribute("username");

		int pageroffset = 0;
		int maxcount = -1; // set -1 if want to get all papers
		List<paper> papers = HibernateUtil.getPaper(pageroffset, maxcount);
		String rootPath = request.getRealPath("/");
		String filePath = "PaperSummary" + File.separator + college_id
				+ "_Summary.xls";
		String targetPath = rootPath + filePath;
		try {
			FileUtil.generateXlsFile(targetPath, papers);
		} catch (JXLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// json to return
		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");
		result.accumulate("path", filePath);
		// ----------------------------------------------
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();
	}
}
