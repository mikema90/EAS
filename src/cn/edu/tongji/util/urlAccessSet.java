package cn.edu.tongji.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Singleton
 */
public class urlAccessSet {
	private static urlAccessSet uniqueInstance = null;

	public Set<String> collegeSet = new HashSet<String>();
	public Set<String> expertSet = new HashSet<String>();
	public Set<String> adminSet = new HashSet<String>();

	private urlAccessSet() {
		// TODO Auto-generated constructor stub
		// put for college
		collegeSet.add("thesisList.html");
		collegeSet.add("uploadThesis.html");
		collegeSet.add("changePwdA.html");
		adminSet.add("changePwdB.html");
		adminSet.add("manageThesisList.html");
		adminSet.add("expertConfig.html");
	}

	public static urlAccessSet getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new urlAccessSet();
		}
		return uniqueInstance;
	}

}
