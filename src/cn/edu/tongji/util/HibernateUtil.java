package cn.edu.tongji.util;

import java.util.List;

import model.college;
import model.expert;
import model.paper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Configuration m_cfg = new Configuration();
	@SuppressWarnings("deprecation")
	private static SessionFactory m_sf = m_cfg.configure()
			.buildSessionFactory();

	// HQL
	private static String expert_login_sql = "from expert where work_id = ? and pwd = ?";
	private static String admin_login_sql = "from admin where work_id = ? and pwd = ?";
	private static String college_login_sql = "from college where college_id = ? and pwd = ?";

	private static String get_all_paper_sql = "from paper order by first_author";
	private static String get_paper_sql = "from paper where college_id = ? order by first_author";
	private static String get_one_paper_sql = "from paper where id = ?";
	private static String get_all_paper_count_sql = "select count(*) from paper";
	private static String get_paper_count_sql = "select count(*) from paper where college_id = ?";
	private static String get_college_sql = "from college";
	private static String get_mapping_sql = "select journal_type from mapping where issues = ?";

	private static String expert_pwdmodify_sql = "update expert set pwd = ? where work_id = ? and pwd = ?";
	private static String admin_pwdmodify_sql = "update admin set pwd = ? where work_id = ? and pwd = ?";
	private static String college_pwdmodify_sql = "update college set pwd = ? where college_id = ? and pwd = ?";
	private static String update_reviewstatus_sql = "update reviewschedule set status = ?, comment = ? where paper_id = ? and expert_work_id = ?";

	private static String reset_college_pwd_sql = "update college set pwd = '8888' where college_id = ?";
	private static String reset_expert_pwd_sql = "update expert set pwd = '8888' where work_id = ?";

	private static String delete_paper_sql = "delete from paper where id = ?";

	// -------------------------------------------------------------------------------
	public HibernateUtil() {
	}

	public static void DeHibernateOperation() {
		m_sf.close();
	}

	/**
	 * check login name and pwd
	 * 
	 * @param identity
	 * @param username
	 * @param pwd
	 * @return
	 */
	public static boolean isPwdValid(String identity, String username,
			String pwd) {
		boolean is_valid = false;
		int size = 0;

		Session session = m_sf.openSession();
		session.beginTransaction();

		if (identity.equals("college")) {
			size = session.createQuery(college_login_sql)
					.setString(0, username).setString(1, pwd).list().size();
		} else if (identity.equals("expert")) {
			size = session.createQuery(expert_login_sql).setString(0, username)
					.setString(1, pwd).list().size();
		} else if (identity.equals("admin")) {
			size = session.createQuery(admin_login_sql).setString(0, username)
					.setString(1, pwd).list().size();
		}

		if (size == 1) {
			is_valid = true;
		}
		session.getTransaction().commit();
		session.close();
		return is_valid;
	}

	/**
	 * add paper into DB
	 * 
	 * @param paper
	 */
	public static void addPaper(paper p) {
		Session session = m_sf.openSession();
		session.beginTransaction();
		System.out.println("start add paper...\n");
		session.save(p);
		System.out.println("complete add paper...\n");
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * update existing paper in DB
	 * 
	 * @param paper
	 */
	public static void updatePaper(paper p) {
		Session session = m_sf.openSession();
		session.beginTransaction();
		System.out.println("start update paper...\n");
		session.update(p);
		System.out.println("complete update paper...\n");
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public static List<paper> getAllPaper(int pageroffset, int maxcount) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		List<paper> papers = session.createQuery(get_all_paper_sql)
				.setFirstResult(pageroffset).setMaxResults(maxcount).list();

		session.getTransaction().commit();
		session.close();

		return papers;
	}

	@SuppressWarnings("unchecked")
	public static List<paper> getPaper(int pageroffset, int maxcount,
			int college_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		List<paper> papers = null;
		if (maxcount == -1) {// get all
			papers = session.createQuery(get_paper_sql)
					.setInteger(0, college_id).list();
		} else { // get specific rows
			papers = session.createQuery(get_paper_sql)
					.setInteger(0, college_id).setFirstResult(pageroffset)
					.setMaxResults(maxcount).list();
		}

		session.getTransaction().commit();
		session.close();

		return papers;
	}

	public static paper getOnePaperCount(int id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		paper p = (paper) session.createQuery(get_one_paper_sql)
				.setInteger(0, id).uniqueResult();

		session.getTransaction().commit();
		session.close();

		return p;
	}

	public static int getAllPaperCount() {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int icount = ((Long) session.createQuery(get_all_paper_count_sql)
				.iterate().next()).intValue();

		session.getTransaction().commit();
		session.close();

		return icount;
	}

	public static int getPaperCount(int college_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int icount = ((Long) session.createQuery(get_paper_count_sql)
				.setInteger(0, college_id).iterate().next()).intValue();

		session.getTransaction().commit();
		session.close();

		return icount;
	}

	/**
	 * add expert into DB
	 * 
	 * @param expert
	 */
	public static void addExpert(expert e) {
		Session session = m_sf.openSession();
		session.beginTransaction();
		System.out.println("start add expert...\n");
		session.save(e);
		System.out.println("complete add expert...\n");
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * update existing expert in DB
	 * 
	 * @param expert
	 */
	public static void updateExpert(expert e) {
		Session session = m_sf.openSession();
		session.beginTransaction();
		System.out.println("start update expert...\n");
		session.update(e);
		System.out.println("complete update expert...\n");
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public static List<college> getCollege() {
		Session session = m_sf.openSession();
		session.beginTransaction();

		// get all
		List<college> colleges = session.createQuery(get_college_sql).list();

		session.getTransaction().commit();
		session.close();

		System.out.println("get college info successfully!");
		// cover pwd before return
		for (int i = 0; i < colleges.size(); i++) {
			colleges.get(i).setPwd("");
		}

		return colleges;
	}

	public static void pwdModify(String identity, String username,
			String cur_pwd, String new_pwd) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int status = 0;
		if (identity.equals("college")) {
			status = session.createQuery(college_pwdmodify_sql)
					.setString(0, new_pwd).setString(1, username)
					.setString(2, cur_pwd).executeUpdate();
		} else if (identity.equals("expert")) {
			status = session.createQuery(expert_pwdmodify_sql)
					.setString(0, new_pwd).setString(1, username)
					.setString(2, cur_pwd).executeUpdate();
		} else if (identity.equals("admin")) {
			status = session.createQuery(admin_pwdmodify_sql)
					.setString(0, new_pwd).setString(1, username)
					.setString(2, cur_pwd).executeUpdate();
		}

		if (status == 1) {
			System.out.println("update pwd successfully!");
		} else {
			System.out.println("update pwd failed!!");
		}
		session.getTransaction().commit();
		session.close();
	}

	public static int updateReviewStatus(String paper_id,
			String expert_work_id, String status, String comment) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int upstatus = session.createQuery(update_reviewstatus_sql)
				.setString(0, status).setString(1, comment)
				.setString(2, paper_id).setString(3, expert_work_id)
				.executeUpdate();

		if (upstatus == 1) {
			System.out.println("update review status successfully!");
		} else {
			System.out.println("update review status failed!!");
		}

		session.getTransaction().commit();
		session.close();

		return upstatus;
	}

	/**
	 * reset college pwd
	 * 
	 * @param college_id
	 */
	public static int resetCollegePwd(int college_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int upstatus = session.createQuery(reset_college_pwd_sql)
				.setInteger(0, college_id).executeUpdate();

		if (upstatus == 1) {
			System.out.println("update college pwd successfully!");
		} else {
			System.out.println("update college pwd failed!!");
		}

		session.getTransaction().commit();
		session.close();

		return upstatus;
	}

	/**
	 * reset expert pwd
	 * 
	 * @param work_id
	 */
	public static int resetExpertPwd(int work_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int upstatus = session.createQuery(reset_expert_pwd_sql)
				.setInteger(0, work_id).executeUpdate();

		if (upstatus == 1) {
			System.out.println("update expert pwd successfully!");
		} else {
			System.out.println("update expert pwd failed!!");
		}

		session.getTransaction().commit();
		session.close();

		return upstatus;
	}

	public static int deletePaper(int paper_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int upstatus = session.createQuery(delete_paper_sql)
				.setInteger(0, paper_id).executeUpdate();

		if (upstatus == 1) {
			System.out.println("delete paper successfully!");
		} else {
			System.out.println("delete paper failed!!");
		}

		session.getTransaction().commit();
		session.close();

		return upstatus;
	}

	public static String getMapping(String issues) {
		Session session = m_sf.openSession();
		session.beginTransaction();
		
		String journal_type = (String) session.createQuery(get_mapping_sql)
		.setString(0, issues).uniqueResult();
		
		System.out.println("get " + issues + "`s journal_type mapping!");
		session.getTransaction().commit();
		session.close();
		
		return journal_type;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// HibernateUtil ho = new HibernateUtil();
		// boolean b = ho.hasPermission("expert", "082928", "123456");
		// System.out.println(b);
		// ho.pwdModify("expert", "1234839", "1234", "123456");
		// HibernateUtil.updateReviewStatus("1", "1234839", "2",
		// "it is not good enough");
		// List<paper> papers = HibernateUtil.getPaper(0, 3, 8800);
		// for (int i = 0; i < papers.size(); i++) {
		// System.out.println(papers.get(i).getTitle());
		// }
		// paper p = new paper();
		// p.setId(2);
		// p.setCollege_name("材料学院");
		// HibernateUtil.updatePaper(p);
		// HibernateUtil.resetExpertPwd(1234839);
		System.out.println(HibernateUtil.getMapping("CN-22-2222"));
		HibernateUtil.DeHibernateOperation();
	}

}
