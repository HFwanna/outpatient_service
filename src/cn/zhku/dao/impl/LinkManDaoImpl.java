package cn.zhku.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import cn.zhku.dao.LinkManDao;
import cn.zhku.domain.LinkMan;
@Repository("linkManDao")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public void save(LinkMan linkMan) {
		getHibernateTemplate().save(linkMan);
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
	public List<LinkMan> getLinkManPageList(DetachedCriteria dc, int start,
			Integer pageSize) {
		return (List<LinkMan>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
	}

	@Override
	public LinkMan getById(Long lkm_cust_id) {
		return getHibernateTemplate().get(LinkMan.class, lkm_cust_id);
	}

	@Override
	public void saveOrUpdate(LinkMan linkMan) {
		getHibernateTemplate().saveOrUpdate(linkMan);
	}

}
