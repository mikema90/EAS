package cn.edu.tongji;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class pdf2SWF {
	private static String SWFTools_HOME = "C:\\Program Files (x86)\\SWFTools";

	public pdf2SWF() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param sourceFile
	 * @param destFile
	 */
	public static void transfer(String sourceFile, String destFile) {

		// create dir if not exist
		File dest = new File(destFile);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		File source = new File(sourceFile);
		if (!source.exists()) {
			System.out.println("sourceFile doesn`t exist!");
		}

		try {
			// command pdf2swf.exe -i sourceFilePath.pdf -o
			// destFilePath.swf
			String command = SWFTools_HOME + "\\pdf2swf.exe -i " + sourceFile
					+ " -o " + destFile;
			Process pro = Runtime.getRuntime().exec(command);

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(pro.getInputStream()));
			while (bufferedReader.readLine() != null) {
			}
			pro.waitFor();
			System.out.println("process exit value is : " + pro.exitValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sourceFile = "C:\\Users\\I301671\\Desktop\\test.pdf", destFile = "C:\\Users\\I301671\\Desktop\\test.swf";
		pdf2SWF.transfer(sourceFile, destFile);
	}

}
