package cn.edu.tongji;

import java.util.List;

import model.expert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listAllExpert();
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void listAllExpert() {
		Configuration cfg = new Configuration();
		SessionFactory sf = cfg.configure().buildSessionFactory();

		Session session = sf.openSession();
		session.beginTransaction();

		List alls = session.createQuery("from expert").list();
		for (int i = 0; i < alls.size(); i++) {
			expert e = (expert) alls.get(i);
			System.out.println(e.getName() + " " + e.isEngineering() + " " + e.isArts());
		}

		session.getTransaction().commit();
		session.close();

		sf.close();
	}
}
