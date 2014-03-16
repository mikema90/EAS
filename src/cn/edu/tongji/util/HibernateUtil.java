package cn.edu.tongji.util;

import java.util.ArrayList;
import java.util.List;

import model.evaluationpaper;
import model.college;
import model.expert;
import model.paper;
import model.reviewschedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Configuration m_cfg = new Configuration();
	@SuppressWarnings("deprecation")
	private static SessionFactory m_sf = m_cfg.configure()
			.buildSessionFactory();

	protected static Logger logger = LogManager.getLogger(HibernateUtil.class
			.getName());

	// HQL
	private static String expert_login_sql = "from expert where work_id = ? and pwd = ?";
	private static String admin_login_sql = "from admin where work_id = ? and pwd = ?";
	private static String college_login_sql = "from college where college_id = ? and pwd = ?";

	private static String get_all_paper_sql = "from paper order by college_id, first_author";
	private static String get_paper_sql = "from paper where college_id = ? order by first_author";
	private static String get_one_paper_sql = "from paper where id = ?";
	private static String get_all_paper_count_sql = "select count(*) from paper";
	private static String get_paper_count_sql = "select count(*) from paper where college_id = ?";
	private static String get_evaluation_paper_sql = "from reviewschedule rs, paper p where rs.paper_id = p.id and rs.expert_work_id = ?";
	private static String get_college_sql = "from college";
	private static String get_mapping_sql = "select journal_type from mapping where issues = ? or issues = ?";
	private static String get_opendeclare_sql = "select opendeclare from admin where work_id = 'admin'";
	private static String get_expert_sql = "from expert order by college_id";

	private static String expert_pwdmodify_sql = "update expert set pwd = ? where work_id = ? and pwd = ?";
	private static String admin_pwdmodify_sql = "update admin set pwd = ? where work_id = ? and pwd = ?";
	private static String college_pwdmodify_sql = "update college set pwd = ? where college_id = ? and pwd = ?";
	private static String save_reviewstatus_sql = "update reviewschedule set status = ?, comment = ? where paper_id = ? and expert_work_id = ?";

	// '77804d2ba1922c33' is the result after MD5 for '888888'
	private static String reset_college_pwd_sql = "update college set pwd = '77804d2ba1922c33' where college_id = ?";
	private static String reset_expert_pwd_sql = "update expert set pwd = '77804d2ba1922c33' where work_id = ?";
	private static String set_declare_status_sql = "update admin set opendeclare = ? where work_id = 'admin'";

	private static String delete_paper_sql = "delete from paper where id = ?";
	private static String delete_expert_sql = "delete from expert where id = ?";

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
		logger.trace(identity + "-" + username + " login status is " + is_valid);
		return is_valid;
	}

	/**
	 * check whether paper declare has been opened
	 * 
	 * @return
	 */
	public static boolean isOpenDeclare() {
		Session session = m_sf.openSession();
		session.beginTransaction();

		boolean isOD = (boolean) session.createQuery(get_opendeclare_sql)
				.uniqueResult();

		session.getTransaction().commit();
		session.close();

		return isOD;
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
			String college_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		List<paper> papers = null;
		if (maxcount == -1) {// get all
			papers = session.createQuery(get_paper_sql)
					.setString(0, college_id).list();
		} else { // get specific rows
			papers = session.createQuery(get_paper_sql)
					.setString(0, college_id).setFirstResult(pageroffset)
					.setMaxResults(maxcount).list();
		}

		session.getTransaction().commit();
		session.close();

		return papers;
	}

	@SuppressWarnings("unchecked")
	public static List<evaluationpaper> getEvaluationPaper(String expert_work_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		List<Object[]> list = session.createQuery(get_evaluation_paper_sql)
				.setString(0, expert_work_id).list();

		List<evaluationpaper> evaluatonpapers = new ArrayList<evaluationpaper>();
		for (Object[] object : list) {
			reviewschedule rs = (reviewschedule) object[0];
			paper p = (paper) object[1];
			// fill in content
			evaluationpaper ep = new evaluationpaper();
			ep.setId(p.getId());
			ep.setCollege_id(p.getCollege_id());
			ep.setCollege_name(p.getCollege_name());
			ep.setCategory(p.getCategory());
			ep.setFirst_author(p.getFirst_author());
			ep.setFirst_author_wid(p.getFirst_author_wid());
			ep.setOther_authors(p.getOther_authors());
			ep.setOther_authors_wid(p.getOther_authors_wid());
			ep.setTitle(p.getTitle());
			ep.setJournal(p.getJournal());
			ep.setIssues(p.getIssues());
			ep.setJournal_type(p.getJournal_type());
			ep.setPost_date(p.getPost_date());
			ep.setLanguage(p.getLanguage());
			ep.setPdf_url(p.getPdf_url());
			ep.setStatus(rs.getStatus());
			ep.setComment(rs.getComment());
			evaluatonpapers.add(ep);
		}

		session.getTransaction().commit();
		session.close();

		return evaluatonpapers;
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

	public static int getPaperCount(String college_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int icount = ((Long) session.createQuery(get_paper_count_sql)
				.setString(0, college_id).iterate().next()).intValue();

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
		// add expert
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
	public static List<expert> getExpert() {
		Session session = m_sf.openSession();
		session.beginTransaction();

		List<expert> experts = session.createQuery(get_expert_sql).list();

		session.getTransaction().commit();
		session.close();

		return experts;
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

	public static int saveReviewStatus(int paper_id,
			String expert_work_id, String status, String comment) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int upstatus = session.createQuery(save_reviewstatus_sql)
				.setString(0, status).setString(1, comment)
				.setInteger(2, paper_id).setString(3, expert_work_id)
				.executeUpdate();

		if (upstatus == 1) {
			System.out.println("save review status successfully!");
		} else {
			System.out.println("save review status failed!!");
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
	public static int resetCollegePwd(String college_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int upstatus = session.createQuery(reset_college_pwd_sql)
				.setString(0, college_id).executeUpdate();

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
	public static int resetExpertPwd(String work_id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int upstatus = session.createQuery(reset_expert_pwd_sql)
				.setString(0, work_id).executeUpdate();

		if (upstatus == 1) {
			System.out.println("update expert pwd successfully!");
		} else {
			System.out.println("update expert pwd failed!!");
		}

		session.getTransaction().commit();
		session.close();

		return upstatus;
	}

	public static int setDeclareStatus(boolean status) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int upstatus = session.createQuery(set_declare_status_sql)
				.setBoolean(0, status).executeUpdate();

		if (upstatus == 1) {
			System.out.println("update declare status to " + status
					+ " successfully!");
		} else {
			System.out.println("update declare status to " + status
					+ " failed!!");
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

	public static int deleteExpert(int id) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int upstatus = session.createQuery(delete_expert_sql).setInteger(0, id)
				.executeUpdate();

		if (upstatus == 1) {
			System.out.println("delete expert successfully!");
		} else {
			System.out.println("delete expert failed!!");
		}

		session.getTransaction().commit();
		session.close();

		return upstatus;
	}

	@SuppressWarnings("unchecked")
	public static String getMapping(String issues, String journal) {
		if (issues.startsWith("ISBN")) {
			return "非核心期刊";
		}
		Session session = m_sf.openSession();
		session.beginTransaction();

		System.out.println("get " + issues + "`s journal_type mapping!");

		List<String> journal_types = session.createQuery(get_mapping_sql)
				.setString(0, issues).setString(1, journal).list();
		String journal_type = "";
		if (journal_types.size() == 0) {
			journal_type = "非核心期刊";
		} else if (journal_types.size() == 1) {
			journal_type = journal_types.get(0);
		} else if (journal_types.size() == 2) { // check whether equal
			if (journal_types.get(0).equals(journal_types.get(1))) {
				journal_type = journal_types.get(0);
			} else {
				journal_type = "#failed#";
			}
		}

		session.getTransaction().commit();
		session.close();

		return journal_type;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(HibernateUtil.setDeclareStatus(true));
		// HibernateUtil.deleteExpert("1234839");
		// List<expert> experts = HibernateUtil.getExpert();
		HibernateUtil.saveReviewStatus(1, "1234839", "yes", "good");
		HibernateUtil.DeHibernateOperation();
	}

}
