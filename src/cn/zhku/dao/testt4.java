package cn.zhku.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.zhku.domain.User;

public class testt4 {
	public void test() {
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User u = session.get(User.class, 1l);
		System.out.println(u);
		
		
		
		
		
		tx.commit();
		session.close();
		sf.close();
	}
}
