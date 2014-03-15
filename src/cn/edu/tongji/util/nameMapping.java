package cn.edu.tongji.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.college;

/**
 * Singleton
 */
public class nameMapping {
	private static nameMapping uniqueInstance = null;

	public Map<String, String> collegeMap = new HashMap<String, String>();
	public Map<String, String> categoryMap = new HashMap<String, String>();
	public Map<String, String> languageMap = new HashMap<String, String>();
	public Map<String, String> subjectMap = new HashMap<String, String>();

	private nameMapping() {
		// TODO Auto-generated constructor stub
		// put for collegeMap
		List<college> colleges = HibernateUtil.getCollege();
		for (int i = 0; i < colleges.size(); i++) {
			college temp = colleges.get(i);
			collegeMap
					.put(String.valueOf(temp.getCollege_id()), temp.getName());
		}

		// put for categoryMap
		categoryMap.put("eduTheory", "教育理论");
		categoryMap.put("eduPractice", "教育实践");
		categoryMap.put("eduTech", "教育技术");
		categoryMap.put("eduMethod", "教育方法");
		categoryMap.put("experimentEdu", "实验教学");
		categoryMap.put("other", "其它");

		// put for languageMap
		languageMap.put("Chinese", "中文");
		languageMap.put("English", "英文");
		languageMap.put("German", "德文");
		languageMap.put("Japanese", "日文");
		languageMap.put("Other", "其他");

		// put for subjectMap
		subjectMap.put("engineering", "工学");
		subjectMap.put("science", "理学");
		subjectMap.put("medicalLife", "医学生命");
		subjectMap.put("otherMajor", "文管艺术及其他");
	}

	public static nameMapping getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new nameMapping();
		}
		return uniqueInstance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
