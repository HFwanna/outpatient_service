package cn.zhku.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.zhku.dao.GuaHaoDao;
import cn.zhku.domain.Registration;
import cn.zhku.service.GuaHaoService;

@Repository("GuaHaoDao")
public class GuaHaoDaoImpl extends BaseDaoImpl<Registration> implements GuaHaoDao{
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public Object[] getByProperty(Registration registration) {
		//根据属性中不为空的属性查询符合记录
		String reg_patientName = registration.getReg_patientName();
		String reg_card = registration.getReg_card();
		Boolean reg_sex = registration.getReg_sex();
		Integer reg_age = registration.getReg_age();
		Long reg_phone = registration.getReg_phone();
		String reg_diseaseType = registration.getReg_diseaseType();
		Long reg_id = registration.getReg_id();
		/*
		 * select reg_patientName,reg_card,reg_sex,reg_age,reg_phone,reg_diseaseType
		 *	from ops_registration r 
		 *	where r.reg_patientName = "陈先生" and r.reg_card = 1 
		 *	and r.reg_sex = 1 and r.reg_age = 18 and r.reg_phone = 123 
		 *	and r.reg_diseaseType = "泌尿科";
		*/
		StringBuffer sql = new StringBuffer("select reg_id,reg_patientName,reg_card,reg_sex,reg_age,reg_phone,reg_diseaseType"+ 
		" from ops_registration r");
		if (reg_patientName!=null&&!reg_patientName.equals("")) {
			sql.append(" where r.reg_patientName = '"+ reg_patientName+"'");	
		}
		if (reg_card!=null&&!reg_card.equals("")) {
			sql.append(" and r.reg_card = '" + reg_card + "'");	
		}
		if (reg_sex!=null&&!reg_sex.equals("")) {
			sql.append(" and r.reg_sex = " + reg_sex);	
		}
		if (reg_age!=null&&!reg_age.equals("")) {
			sql.append(" and r.reg_age = " + reg_age);	
		}
		if (reg_phone!=null&&!reg_phone.equals("")) {
			sql.append(" and r.reg_phone = " + reg_phone);	
		}
		if (reg_diseaseType!=null&&!reg_diseaseType.equals("")) {
			sql.append(" and r.reg_diseaseType = '" + reg_diseaseType + "'");	
		}
		String sq = sql.toString();
		return getHibernateTemplate().execute(new HibernateCallback<Object[]>() {

			@Override
			public Object[] doInHibernate(Session session)
					throws HibernateException {
				//
				SQLQuery query = session.createSQLQuery(sq);
				if (query.list()!=null && query.list().size()>0) {
					List list = query.list();
					System.out.println(list);
					return (Object[]) query.list().get(0);
				}
				return null;
			}
			
		});
		
	}

	@Override
	public Integer getMaxNumInDb() {
		//获取数据库中最大的流水号
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException {
				//
				SQLQuery query = session.createSQLQuery("select max(reg_serialNumber) from ops_registration");
				Integer result = (Integer) query.uniqueResult();
				return result;
			}
			
		});
	}
}
