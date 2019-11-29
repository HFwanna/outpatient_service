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

import cn.zhku.ajax.dao.AjaxPrescriptionDao;
import cn.zhku.dao.impl.BaseDaoImpl;
import cn.zhku.domain.DrugMessage;
import cn.zhku.domain.Prescription;

@Repository("ajaxPrescriptionDao")
public class AjaxPrescriptionDaoImpl extends BaseDaoImpl<Prescription> implements AjaxPrescriptionDao {
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
					Prescription prescription = session.get(Prescription.class, id);
					session.delete(prescription);
					System.out.println("AjaxPrescriptionDaoImpl的deleteByIdList方法批量删除成功");
				}
				return null;
			}
		});
	}

	@Override
	public void saveOrUpdate(List<Prescription> prelist) {
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				//先把list元素取出来
				for(Prescription prescription : prelist) {
					session.saveOrUpdate(prescription);
					System.out.println("AjaxPrescriptionDaoImpl的saveOrUpdate方法批量保存成功");
				}
				return null;
			}
		});
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<DrugMessage> getDrugMessage(DetachedCriteria dCriteria) {
		//获取prescription的list
				List<DrugMessage> list = null;
				try {
					list = (List<DrugMessage>) getHibernateTemplate()
							.findByCriteria(dCriteria);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return list;
	}


	@Override
	public Long getDrugIdByDrugName(String name) {
		try {
			//通过药物名字搜索药物id
			String queryString = "select d.drug_id From DrugMessage d where drug_name = :name";
			return getHibernateTemplate().execute(new HibernateCallback<Long>() {
				@Override
				public Long doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(queryString);
					query.setParameter("name", name);
					Long result = (Long) query.uniqueResult();
					System.out.println("leileleileilelelelelelellelelelelelelelleel"+result);
					return result;
				}
			});
		} catch (Exception e) {
			System.out.println("AjaxPrescriptionDaoImpl的getDrugIdByDrugName方法出错了");
			e.printStackTrace();
			return null;
		}
		
	}


	@Override
	public String getDrugMessageByDrugName(String content) {
		try {
			//通过药物名字搜索药物id
			String queryString = "select d.drug_message From DrugMessage d where drug_name = :name";
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
			System.out.println("AjaxPrescriptionDaoImpl的getDrugMessageByDrugName方法出错了");
			e.printStackTrace();
			return null;
		}
	}

}
