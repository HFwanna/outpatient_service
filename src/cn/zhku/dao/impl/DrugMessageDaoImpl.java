package cn.zhku.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.zhku.dao.DrugMessageDao;
@Repository("drugMessageDao")
public class DrugMessageDaoImpl extends HibernateDaoSupport implements DrugMessageDao {
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public Integer getTotalCount(DetachedCriteria dc) {
		dc.setProjection(Projections.rowCount());
		List list = getHibernateTemplate().findByCriteria(dc);
		dc.setProjection(null);
		if (list!=null&&list.size()>0) {
			Long count = (Long) list.get(0);
//			System.out.println(count);
			return count.intValue();
		}
		return null;
	}

	@Override
	public List getDrugMessageList(DetachedCriteria dc, int start,
			Integer rows) {
		return getHibernateTemplate().findByCriteria(dc, start, rows);
	}

}
