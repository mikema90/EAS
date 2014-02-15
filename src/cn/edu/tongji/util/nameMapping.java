package cn.edu.tongji;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton
 */
public class nameMapping {
	private static nameMapping uniqueInstance = null;
	
	public Map<String, String> collegeMap = new HashMap<String, String>();
	public Map<String, String> categoryMap = new HashMap<String, String>();
	public Map<String, String> languageMap = new HashMap<String, String>();
	
	private nameMapping() {
		// TODO Auto-generated constructor stub
		// put for collegeMap
		collegeMap.put("automobile", "汽车学院");
		collegeMap.put("caup", "建筑与城市规划学院");
		collegeMap.put("software", "软件学院");
		
		// put for categoryMap
		categoryMap.put("eduTheory", "教育理论");
		categoryMap.put("eduPractice", "教育实践");
		categoryMap.put("eduTech", "教育技术");
		categoryMap.put("eduMethod", "教育方法");
		categoryMap.put("experimentEdu", "实验教学");
		categoryMap.put("other", "其它");
		
		// put for languageMap
		languageMap.put("No", "中文");
		languageMap.put("English", "英文");
		languageMap.put("German", "德文");
		languageMap.put("Japanese", "日文");
		languageMap.put("Other", "其他");
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
