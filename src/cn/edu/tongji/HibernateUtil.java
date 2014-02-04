package cn.edu.tongji;

import java.util.List;

import model.paper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Configuration m_cfg = new Configuration();
	@SuppressWarnings("deprecation")
	private static SessionFactory m_sf = m_cfg.configure()
			.buildSessionFactory();

	// SQL
	private static String expert_login_sql = "from expert where work_id = ? and pwd = ?";
	private static String admin_login_sql = "from admin where work_id = ? and pwd = ?";
	private static String teacher_login_sql = "from teacher where work_id = ? and pwd = ?";
	private static String get_paper_sql = "from paper";
	private static String expert_pwdmodify_sql = "update expert set pwd = ? where work_id = ? and pwd = ?";
	private static String admin_pwdmodify_sql = "update admin set pwd = ? where work_id = ? and pwd = ?";
	private static String teacher_pwdmodify_sql = "update teacher set pwd = ? where work_id = ? and pwd = ?";
	private static String update_reviewstatus_sql = "update reviewschedule set status = ?, comment = ? where paper_id = ? and expert_work_id = ?";

	// -------------------------------------------------------------------------------
	public HibernateUtil() {
	}

	public static void DeHibernateOperation() {
		m_sf.close();
	}

	public static boolean isPwdValid(String identity, String username,
			String pwd) {
		boolean is_valid = false;
		int size = 0;

		Session session = m_sf.openSession();
		session.beginTransaction();

		if (identity.equals("teacher")) {
			size = session.createQuery(teacher_login_sql)
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
	 * @param paper_id
	 */
	public static void updatePaper(String paper_id){
		
	}

	@SuppressWarnings("unchecked")
	public static List<paper> getPaper() {
		Session session = m_sf.openSession();
		session.beginTransaction();

		List<paper> papers = session.createQuery(get_paper_sql).list();

		session.getTransaction().commit();
		session.close();

		return papers;
	}

	public static void pwdModify(String identity, String username,
			String cur_pwd, String new_pwd) {
		Session session = m_sf.openSession();
		session.beginTransaction();

		int status = 0;
		if (identity.equals("teacher")) {
			status = session.createQuery(teacher_pwdmodify_sql)
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

	public static void updateReviewStatus(String paper_id,
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// HibernateUtil ho = new HibernateUtil();
		// boolean b = ho.hasPermission("expert", "082928", "123456");
		// System.out.println(b);
		// ho.pwdModify("expert", "1234839", "1234", "123456");
		HibernateUtil.updateReviewStatus("1", "1234839", "2",
				"it is not good enough");
		HibernateUtil.DeHibernateOperation();
	}

}
