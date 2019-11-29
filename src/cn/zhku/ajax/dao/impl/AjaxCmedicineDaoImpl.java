package cn.zhku.ajax.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.zhku.ajax.dao.AjaxCmedicineDao;
import cn.zhku.dao.impl.BaseDaoImpl;
import cn.zhku.domain.Cmedicine;
import cn.zhku.domain.DrugMessage2;

@Repository("ajaxCmedicineDao")
public class AjaxCmedicineDaoImpl extends BaseDaoImpl<Cmedicine> implements AjaxCmedicineDao {
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void deleteByIdList(List<Long> deleteIdList) {
		//批量删除
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				//先把list元素取出来
				for(Long id : deleteIdList) {
					//先查再删
					Cmedicine cmedicine = session.get(Cmedicine.class, id);
					session.delete(cmedicine);
					System.out.println("AjaxCmedicineDaoImpl的deleteByIdList方法批量删除成功");
				}
				return null;
			}
		});
	}

	@Override
	public void saveOrUpdate(List<Cmedicine> cmedicines) {
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				//先把list元素取出来
				for(Cmedicine cmedicine : cmedicines) {
					session.saveOrUpdate(cmedicine);
					System.out.println("AjaxCmedicineDaoImpl的saveOrUpdate方法批量保存成功");
				}
				return null;
			}
		});
	}

	@Override
	public Long getDrugIdByDrugName(String name) {
		try {
			//通过药物名字搜索药物id
			String queryString = "select d.drug_id From DrugMessage2 d where drug_name = :name";
			
			return getHibernateTemplate().execute(new HibernateCallback<Long>() {
				@Override
				public Long doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(queryString);
					query.setParameter("name", name);
					Long result = (Long) query.uniqueResult();
					return result;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DrugMessage2> getDrugMessage2(DetachedCriteria dCriteria) {
		//获取drugMessage2的list
		List<DrugMessage2> list = null;
		try {
			list = (List<DrugMessage2>) getHibernateTemplate()
					.findByCriteria(dCriteria);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getDrugMessageByDrugName(String content) {
		try {
			//通过药物名字搜索药物id
			String queryString = "select d.drug_message From DrugMessage2 d where drug_name = :name";
			
			return getHibernateTemplate().execute(new HibernateCallback<String>() {
				@Override
				public String doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(queryString);
					query.setParameter("name", content);
					String result = (String) query.uniqueResult();
					return result;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
