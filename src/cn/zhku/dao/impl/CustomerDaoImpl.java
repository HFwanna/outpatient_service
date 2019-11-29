package cn.zhku.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.zhku.dao.CustomerDao;
import cn.zhku.domain.Customer;
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
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
	public List<Customer> getCustomerPageList(DetachedCriteria dc, int start,
			Integer pageSize) {
/*		Session session = currentSession();
		Session session2 = getSessionFactory().getCurrentSession();
		Session session3 = getSessionFactory().openSession();
		System.out.println("session和session2比较:"+(session==session2));
		System.out.println("session和session3比较:"+(session==session3));
		System.out.println("session2和session3比较:"+(session3==session2));
//		Criteria criteria = dc.getExecutableCriteria(session);
//		int size = criteria.list().size();
//		System.out.println("criteria:++++++++++++++++++++++"+size);
		
		Integer integer = getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session s)
					throws HibernateException {
				System.out.println(session==s);
				System.out.println(session2==s);
				System.out.println(session3==s);
				int size = 1;
				return size;
			}
			
		});
		System.out.println("criteria:++++++++++++++++++++++"+integer);*/
		return (List<Customer>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		getHibernateTemplate().saveOrUpdate(customer);
	}

	@Override
	public List<Object[]> getIndustryCount() {
		String sql= " Select bd.`dict_item_name`,count(*) total  " +
					" From cst_customer c,base_dict bd            "+
					" Where c.`cust_industry` = bd.`dict_id`      "+
					" Group By c.`cust_industry`                  ";
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {

			@SuppressWarnings("unchecked")
			@Override
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
			
		});
		return list;
	}

}
