package cn.edu.tongji.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import model.paper;
import jxl.JXLException;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FileUtil {

	public FileUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String renameFile(String sourcePath, String newName) {
		File sourceFile = new File(sourcePath);
		String oldName = sourceFile.getName();
		String targetPath = sourcePath.replace(oldName, newName);
		File targetFile = new File(targetPath);
		sourceFile.renameTo(targetFile);

		System.out.println("rename file successfully!");
		return targetPath;
	}

	public static void deleteFile(String filePath) {
		File delFile = new File(filePath);
		if (delFile.delete()) {
			System.out.println("delete file successfully");
		} else {
			System.out.println("delete file failed");
		}
	}

	public static void copyFile(String sourcePath, String targetPath)
			throws IOException {
		File sourceFile = new File(sourcePath);
		File targetFile = new File(targetPath);
		// mkdir if dir doesn`t exist
		File parent = targetFile.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		targetFile.createNewFile();

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

	@SuppressWarnings("deprecation")
	public static void generateXlsFile(String targetPath, List<paper> papers)
			throws IOException, JXLException {
		// ------mkdir if dir doesn`t exist------
		File targetFile = new File(targetPath);
		File parent = targetFile.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		targetFile.createNewFile();
		// --------------set format--------------
		WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 14,
				WritableFont.BOLD);
		WritableFont MiddleFont = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.BOLD);
		WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);

		WritableCellFormat wcf_title = new WritableCellFormat(BoldFont);
		wcf_title.setBorder(Border.NONE, BorderLineStyle.THIN);
		wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_title.setAlignment(Alignment.CENTRE);
		wcf_title.setWrap(true);

		WritableCellFormat wcf_table = new WritableCellFormat(MiddleFont);
		wcf_table.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf_table.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_table.setAlignment(Alignment.CENTRE);
		wcf_table.setBackground(Colour.GRAY_25);
		wcf_table.setWrap(true);

		WritableCellFormat wcf_center = new WritableCellFormat(NormalFont);
		wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_center.setAlignment(Alignment.CENTRE);
		wcf_center.setWrap(true);
		// --------------------------------------------

		WritableWorkbook workbook = Workbook.createWorkbook(targetFile);
		WritableSheet sheet = workbook.createSheet("教学论文汇总表", 0);
		sheet.getSettings().setRightMargin(0.5);
		// set column width
		sheet.setColumnView(0, 8);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 10);
		sheet.setColumnView(3, 30);
		sheet.setColumnView(4, 30);
		sheet.setColumnView(5, 25);
		sheet.setColumnView(6, 20);
		sheet.setColumnView(7, 12);
		sheet.setColumnView(8, 12);
		// title
		sheet.setRowView(0, 600, false);
		sheet.mergeCells(0, 0, 8, 0);
		sheet.addCell(new Label(0, 0, "______年教学论文汇总表", wcf_title));

		// table title
		sheet.addCell(new Label(0, 1, "序号", wcf_table));
		sheet.addCell(new Label(1, 1, "学院（系）", wcf_table));
		sheet.addCell(new Label(2, 1, "姓名", wcf_table));
		sheet.addCell(new Label(3, 1, "论文名称", wcf_table));
		sheet.addCell(new Label(4, 1, "期刊名称", wcf_table));
		sheet.addCell(new Label(5, 1, "刊号", wcf_table));
		sheet.addCell(new Label(6, 1, "发表时间", wcf_table));
		sheet.addCell(new Label(7, 1, "是否外文", wcf_table));
		sheet.addCell(new Label(8, 1, "是否属于核心", wcf_table));

		// table content
		int content_row_index = 2;
		for (int i = 0; i < papers.size(); i++) {
			paper temp = papers.get(i);
			Date post_date = temp.getPost_date();
			String date = String.valueOf(post_date.getYear()) + "年"
					+ String.valueOf(post_date.getMonth()) + "月";
			String coreJournal = temp.getJournal_type() != null ? temp
					.getJournal_type() : "否";
			sheet.addCell(new Label(0, content_row_index + i, String
					.valueOf(i + 1), wcf_center));
			sheet.addCell(new Label(1, content_row_index + i, temp
					.getCollege_name(), wcf_center));
			sheet.addCell(new Label(2, content_row_index + i, temp
					.getFirst_author(), wcf_center));
			sheet.addCell(new Label(3, content_row_index + i, temp.getTitle(),
					wcf_center));
			sheet.addCell(new Label(4, content_row_index + i,
					temp.getJournal(), wcf_center));
			sheet.addCell(new Label(5, content_row_index + i, temp.getIssues(),
					wcf_center));
			sheet.addCell(new Label(6, content_row_index + i, date, wcf_center));
			sheet.addCell(new Label(7, content_row_index + i, temp
					.getLanguage(), wcf_center));
			sheet.addCell(new Label(8, content_row_index + i, coreJournal,
					wcf_center));
		}
		// table footer
		int blank_distinct = 5;
		int footer_pos = content_row_index + papers.size() + blank_distinct;
		sheet.setRowView(footer_pos, 500, false);
		sheet.setRowView(footer_pos + 1, 1000, false);
		sheet.mergeCells(5, footer_pos, 8, footer_pos);
		sheet.mergeCells(5, footer_pos + 1, 8, footer_pos + 1);
		sheet.addCell(new Label(5, footer_pos, "院长签名：__________", wcf_title));
		sheet.addCell(new Label(5, footer_pos + 1, "院系盖章：__________", wcf_title));
		// write into file
		workbook.write();
		workbook.close();
		System.out.println("generate xls file successfully");
	}

	public static void main(String[] args) throws IOException, JXLException {
		// TODO Auto-generated method stub
		// paper temp = new paper();
		// temp.setCollege_id(88);
		// temp.setCollege_name("软件学院");
		// temp.setPost_date(new Date(2013, 11, 1));
		// List<paper>papers = new ArrayList<paper>();
		// papers.add(temp);
		//
		// generateXlsFile("C:\\jxl\\教学汇总表.xls", papers);
		// System.out.println(renameFile("C:\\jxl\\mike.pdf", "jack.pdf"));
		deleteFile("C:\\jxl\\I am mike.pdf");
	}

}
