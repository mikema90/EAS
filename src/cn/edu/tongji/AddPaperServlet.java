package cn.edu.tongji;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		paper p = fillinPaper(request);
		HibernateUtil.addPaper(p);

		JSONObject result = new JSONObject();
		result.accumulate("Status", "success");
		result.accumulate("redirectUrl", "thesisList.html");
		
		System.out.println(result.toString());
		out.write(result.toString());
		out.flush();
		out.close();

	}

	@SuppressWarnings("deprecation")
	public paper fillinPaper(HttpServletRequest request) throws IOException {
		String teacher_work_id = (String) request.getAttribute("username");
		String college = request.getParameter("school"), category = request
				.getParameter("thesisType"), title = request
				.getParameter("thesisName"), journal = request
				.getParameter("periodicalName"), CN = request
				.getParameter("periodicalType"), year = request
				.getParameter("timeYear"), month = request
				.getParameter("timeMonth"), language = request
				.getParameter("thesisLanguage"), fileTmpName = request
				.getParameter("fileTempName");
		String[] authorIDs = request.getParameterValues("authorId"), journalSN = request
				.getParameterValues("periodicalSn1");
		
		Date post_date = new Date(Integer.valueOf(year) - 1900, Integer.valueOf(month) - 1, 1);
		boolean passed = false;
		
		// for testing --delete later
		teacher_work_id = "1234839";
		
		String rootPath = request.getRealPath("/");
		String tmpPath = rootPath + "tempUploadedFile" + File.separator + fileTmpName;
		String pdf_url = teacher_work_id + File.separator + title + ".pdf";
		// move file to unified dir
		FileUtil.copyFile(tmpPath, rootPath + pdf_url);
		
		paper p = new paper();
		// fill data into paper
		p.setTeacher_work_id(Integer.valueOf(teacher_work_id));
		p.setCollege(nameMapping.getInstance().collegeMap.get(college));
		p.setCategory(nameMapping.getInstance().categoryMap.get(category));
		
		String authors = "";
		for(int i = 0; i < authorIDs.length; i++){
			authors = authors + authorIDs[i] + ",";
		}
		p.setAuthors(authors);
		p.setTitle(title);
		p.setJournal(journal);
		if(CN.equals("ISSN")){
			p.setIssn(journalSN[0]);
		}else if(CN.equals("ISBN")){
			p.setIsbn(journalSN[0]);
		}
		p.setJournal_type(journalSN[1]);
		p.setPost_date(post_date);
		p.setLanguage(nameMapping.getInstance().languageMap.get(language));
		p.setPdf_url(pdf_url); 
		p.setPassed(passed);

		return p;
	}
}
