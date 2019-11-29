package cn.zhku.ajax.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import cn.zhku.ajax.dao.AjaxRegistrationDao;
import cn.zhku.dao.impl.BaseDaoImpl;
import cn.zhku.domain.Registration;

@SuppressWarnings("all")
@Repository("ajaxRegistrationDao")
public class AjaxRegistrationDaoImpl extends BaseDaoImpl<Registration> implements AjaxRegistrationDao {
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	
	@Override
	public List<Registration> getRegistration(DetachedCriteria dc) {
		return (List<Registration>) getHibernateTemplate().findByCriteria(dc);
	}
}
