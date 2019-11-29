package cn.zhku.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;


import cn.zhku.dao.RegistrationDao;
import cn.zhku.domain.Registration;
@Repository("registrationDao")
public class RegistrationDaoImpl extends BaseDaoImpl<Registration> implements RegistrationDao {
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public List<Registration> updateRegistrationById(Long reg_id) {
		return getHibernateTemplate().execute(new HibernateCallback<List<Registration>>() {

			@Override
			public List<Registration> doInHibernate(Session session)
					throws HibernateException {
				String hql = "update Registration set reg_state = 1 where reg_id = :reg_id";
				Query query = session.createQuery(hql);
				query.setParameter("reg_id", reg_id);
				int ret = query.executeUpdate();
				System.out.println("RegistrationDaoImpl下的更新操作返回更新成功个数"+ret);
				return null;
			}
		});
	}


}
