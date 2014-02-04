package cn.edu.tongji;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class uploadFile
 */
@WebServlet("/uploadFile")
@MultipartConfig	//标识Servlet支持文件上传@MultipartConfig//标识Servlet支持文件上传
public class uploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String TEMP_FILES_DATA="tempFileData";

    /**
     * Default constructor. 
     */
    public uploadFile() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//存储路径
		String storePath = request.getServletContext().getRealPath("/tempUploadedFile");
		
		Part part = request.getPart("file");
		
		//Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
		//获取请求头
		String header = part.getHeader("content-disposition");
		//获取文件名
		String fileName = parseFileName(header);
		
		String tempName = UUID.randomUUID().toString()+".pdf";
		//把文件写到指定路径
		part.write(storePath+File.separator+tempName);
		
		HashMap<String, String> tempFilesData=(HashMap) request.getSession().getAttribute(TEMP_FILES_DATA);
		if(tempFilesData==null){
			tempFilesData=new HashMap<String, String>();
			request.getSession().setAttribute(TEMP_FILES_DATA, tempFilesData);
		}
		
		tempFilesData.put(tempName, fileName);
		
		PrintWriter out = response.getWriter();
		out.println("<script>parent.uploadingSuccess('"+tempName+"')</script>");
		out.flush();
		out.close();
		System.out.println(storePath+File.separator+tempName);
	}
	
	/**
	 * 根据请求头解析出文件名
	 * 请求头的格式：form-data; name="file"; filename="a.txt"
	 * @param header
	 * @return
	 */
	public String parseFileName(String header) {
		return header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
	}

}
