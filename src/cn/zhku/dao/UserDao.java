package cn.zhku.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.zhku.dao.impl.BaseDaoImpl;
import cn.zhku.domain.Doctor;
import cn.zhku.domain.User;
@Repository("userDao")
public class UserDao extends BaseDaoImpl<Doctor> {
//	SessionFactory sessionFactory;
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public Doctor getByUsername(Doctor user) {
		return getHibernateTemplate().execute(new HibernateCallback<Doctor>() {

			@Override
			public Doctor doInHibernate(Session session) throws HibernateException {
				String hql = "from Doctor where doc_name=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, user.getDoc_name());
				Doctor u = (Doctor) query.uniqueResult();
				return u;
			}
			
		});
	}

	public void saveUsercode() {
		// TODO Auto-generated method stub
		getHibernateTemplate().execute(new HibernateCallback<Doctor>() {

			@Override
			public Doctor doInHibernate(Session session) throws HibernateException {
				
				return null;
			}
		});
	}


}
