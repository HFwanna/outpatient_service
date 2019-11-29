package cn.zhku.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.zhku.dao.BaseDictDao;
import cn.zhku.domain.BaseDict;
@Repository("baseDictDao")
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {
	
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		//根据strig字段获取相应的列的记录
		DetachedCriteria dCriteria = DetachedCriteria.forClass(BaseDict.class);
		dCriteria.add(Restrictions.eq("dict_type_code",dict_type_code));
		List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dCriteria);
		return list;
	}

}
