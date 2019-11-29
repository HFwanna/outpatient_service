package cn.zhku.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import cn.zhku.dao.SaleVisitDao;
import cn.zhku.domain.SaleVisit;
@Repository("saleVisitDao")
public class SaleVisitDaoImpl extends BaseDaoImpl<SaleVisit> implements SaleVisitDao {
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
	public List<SaleVisit> getSaleVisitPageList(DetachedCriteria dc, int start,
			Integer pageSize) {
		return (List<SaleVisit>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
	}

	@Override
	public SaleVisit getById(String visit_id) {
		return getHibernateTemplate().get(SaleVisit.class, visit_id) ;
	}
	

	
}
