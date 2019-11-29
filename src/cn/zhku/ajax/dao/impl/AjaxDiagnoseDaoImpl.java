package cn.zhku.ajax.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.zhku.ajax.dao.ajaxDiagnoseDao;
import cn.zhku.dao.impl.BaseDaoImpl;

@Repository("ajaxDiagnoseDao")
public class AjaxDiagnoseDaoImpl extends BaseDaoImpl<Object> implements ajaxDiagnoseDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List getDiagnoseMessageById(Long reg_id) {
		//调用模板代码,内容是[{"dis_diagnose":"","dis_doctor":""},{"dis_diagnose":"","dis_doctor":""}]
		String hql = "select d.dis_diagnose,d.dis_doctor from DiseaseType d"+
		" where registration.reg_id = :id";
		return getHibernateTemplate().execute(new HibernateCallback<List>() {
			@Override
			public List doInHibernate(Session session)
					throws HibernateException {
				//查询病人名字和就诊日期
				Query query = session.createQuery(hql);
				query.setParameter("id", reg_id);
				List<Object[]> list = query.list();//[[dis_diagnose,dis_doctor],[]...]
				List<Map<String, String>> list2 = new ArrayList<>();
				for(int i = 0 ; i<list.size();i++) {
					Map<String, String> map = new HashMap<>();
					map.put("dis_diagnose", (String) list.get(i)[0]);
					map.put("dis_doctor", (String) list.get(i)[1]);
					list2.add(map);
				}
				return list2;
			}
		});
	}

	@Override
	public List getRegistrationById(Long reg_id) {
		//调用模板代码,内容是[{"reg_patientName":"","reg_date":""}]
		String hql = "select r.reg_patientName,r.reg_date from Registration r" + 
		" where reg_id = :id" ;
		return getHibernateTemplate().execute(new HibernateCallback<List<Map<String, String>>>() {
			@Override
			public List<Map<String, String>> doInHibernate(Session session)
					throws HibernateException {
				//查询病人名字和就诊日期
				Query query = session.createQuery(hql);
				query.setParameter("id", reg_id);
				Object[] objs =  (Object[]) query.uniqueResult();//【】，【】，【】   记录数：3
				Map<String,String> map  = new HashMap<>();
				map.put("reg_patientName", (String) objs[0]);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(objs[1]);
				map.put("reg_date", dateString);
				List<Map<String, String>> list = new ArrayList<>();
				list.add(map);
				return list;
			}
		});
	}

	@Override
	public List getPrescriptionById(Long reg_id) {
		//调用模板代码,内容是[{"pre_totalCount":"","pre_drug_name":""},
		//{"pre_totalCount":"","pre_drug_name":""},{"pre_totalCount":"","pre_drug_name":""}]
		String hql = "select p.pre_totalCount,p.pre_drug_name,p.pre_drug_price from Prescription p" +
		" where pre_registration.reg_id = :id";
		return getHibernateTemplate().execute(new HibernateCallback<List>() {
			@Override
			public List doInHibernate(Session session)
					throws HibernateException {
				//查询病人名字和就诊日期
				Query query = session.createQuery(hql);
				query.setParameter("id", reg_id);
				List<Object[]> list = query.list();//[[pre_totalCount,pre_drug_name],[]...]
				List<Map<String, String>> list2 = new ArrayList<>();
				for(int i = 0 ; i<list.size();i++) {
					Map<String, String> map = new HashMap<>();
					String totalCount = (String) list.get(i)[0];
					String pattern = "(\\d*)(\\D*)";
					Pattern r = Pattern.compile(pattern);
					Matcher m = r.matcher(totalCount);
					String group1 = null;
					if (m.find()) {
						//这里如果只用一个分组(\\d*)，那么group(0)和group(1)同样效果，如果有三个分组(A)(B)
						//group(0) = (A)(B) ; group(1) = (A) ; group(2) = (B)
						group1 = m.group(0);
					}
					map.put("pre_totalCount", group1);
					map.put("pre_drug_name", (String) list.get(i)[1]);
					map.put("pre_drug_price", String.valueOf(list.get(i)[2]));
					list2.add(map);
				}
				return list2;
			}
		});
	}
	
	

}
