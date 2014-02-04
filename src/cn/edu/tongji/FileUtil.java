package cn.edu.tongji;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

	public FileUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param sourcePath
	 *            -tmp location of upload file
	 * @param teacher_work_id
	 *            -store file into folder depend on teacher_work_id
	 * @param title
	 *            - paper title
	 * @return unified directory path
	 * @throws IOException 
	 */
	public String moveFileToUnifiedDir(String sourcePath, String teacher_work_id,
			String title) throws IOException {
		String targetPath = "/" + teacher_work_id + "/" + title + ".pdf";
		copyFile(sourcePath, targetPath);
		return targetPath;
	}

	public static void copyFile(String sourcePath, String targetPath)
			throws IOException {
		File sourceFile = new File(sourcePath);
		File targetFile = new File(targetPath);
		
		
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// create file input stream and buffer for it
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			// create file output stream and buffer for it
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// buffer array
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();
		} finally {
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
