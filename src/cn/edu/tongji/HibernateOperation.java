package cn.edu.tongji;

import java.util.List;

import model.paper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateOperation {
	private static Configuration m_cfg = null;
	private static SessionFactory m_sf = null;

	// SQL
	private static String expert_login_sql = "from expert where work_id = ? and pwd = ?";
	private static String admin_login_sql = "from admin where work_id = ? and pwd = ?";
	private static String teacher_login_sql = "from teacher where work_id = ? and pwd = ?";
	private static String get_paper_sql = "from paper";
	private static String expert_pwdmodify_sql = "update expert set pwd = ? where work_id = ? and pwd = ?";
	private static String admin_pwdmodify_sql = "update admin set pwd = ? where work_id = ? and pwd = ?";
	private static String teacher_pwdmodify_sql = "update teacher set pwd = ? where work_id = ? and pwd = ?";

	// -------------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	public HibernateOperation() {
		m_cfg = new Configuration();
		m_sf = m_cfg.configure().buildSessionFactory();
	}

	public void DeHibernateOperation() {
		m_sf.close();
	}

	public boolean hasPermission(String identity, String username, String pwd) {
		boolean isPermission = false;
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
			isPermission = true;
		}
		session.getTransaction().commit();
		session.close();
		return isPermission;
	}

	/**
	 * add paper into DB
	 * 
	 * @param paper
	 */
	public void addPaper(paper p) {
		Session session = m_sf.openSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<paper> getPaper() {
		Session session = m_sf.openSession();
		session.beginTransaction();

		List<paper> papers = session.createQuery(get_paper_sql).list();

		session.getTransaction().commit();
		session.close();

		return papers;
	}

	public void pwdModify(String identity, String username, String cur_pwd,
			String new_pwd) {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateOperation ho = new HibernateOperation();
		// boolean b = ho.hasPermission("expert", "082928", "123456");
		// System.out.println(b);
		ho.pwdModify("expert", "1234839", "1234", "123456");
		ho.DeHibernateOperation();
	}

}
