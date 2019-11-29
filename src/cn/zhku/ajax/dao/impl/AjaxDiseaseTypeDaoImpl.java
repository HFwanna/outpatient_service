package cn.zhku.ajax.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.zhku.ajax.dao.AjaxDiseaseTypeDao;
import cn.zhku.dao.impl.BaseDaoImpl;
import cn.zhku.domain.DiseaseType;

@SuppressWarnings("all")
@Repository("ajaxDiseaseTypeDao")
public class AjaxDiseaseTypeDaoImpl extends BaseDaoImpl<DiseaseType> implements AjaxDiseaseTypeDao {
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public List<DiseaseType> getDiseaseType(DetachedCriteria dc) {
		// 根据dc查询List<DiseaseType>
		return (List<DiseaseType>) getHibernateTemplate().findByCriteria(dc);
	}

	@Override
	public void saveOrUpdate(List<DiseaseType> dislist) {
		getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				//先把list元素取出来
				for(DiseaseType diseaseType : dislist) {
					session.saveOrUpdate(diseaseType);
					System.out.println("AjaxDiseaseTypeDaoImpl的saveOrUpdate方法批量保存成功");
				}
				return null;
			}
		});
		
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
					DiseaseType diseaseType = session.get(DiseaseType.class, id);
					session.delete(diseaseType);
					System.out.println("AjaxDiseaseTypeDaoImpl的deleteByIdList方法批量删除成功");
				}
				return null;
			}
		});
	}

	@Override
	public List getDiagnose() {
		//获取诊断信息
		List find = getHibernateTemplate().find("from Diagnose");
		return find;
	}
	

}
