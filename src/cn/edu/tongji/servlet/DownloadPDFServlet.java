package cn.edu.tongji.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.tongji.util.CommonFuncInServlet;

@WebServlet("/downlaodPDF")
@SuppressWarnings("serial")
public class DownloadPDFServlet extends HttpServlet {

	public DownloadPDFServlet() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// cover Chinese character
		CommonFuncInServlet.setCharacterEncoding(request, response);

		String fileID = request.getParameter("fileID");
		String filePath = request.getRealPath("/");// + file path stored in DB;

		File downloadFile = new File(filePath);
		if (downloadFile.exists()) {
			System.out.println("file exist");

			Long length = downloadFile.length();
			response.setContentLength(length.intValue());
			String fileName = URLEncoder
					.encode(downloadFile.getName(), "utf-8");
			response.addHeader("Content-Disposition", "attachment; filename="
					+ fileName);

			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			FileInputStream fileInputStream = new FileInputStream(downloadFile);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					fileInputStream);
			int size = 0;
			byte[] b = new byte[4096];
			while ((size = bufferedInputStream.read(b)) != -1) {
				System.out.println("write to output stream..");
				servletOutputStream.write(b, 0, size);
			}
			servletOutputStream.flush();
			servletOutputStream.close();
			bufferedInputStream.close();
		} else {
			System.out.println("File is not exist");
		}
	}

}
