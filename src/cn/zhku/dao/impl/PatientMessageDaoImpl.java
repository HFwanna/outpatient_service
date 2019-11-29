package cn.zhku.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.zhku.dao.PatientMessageDao;
import cn.zhku.domain.DiseaseType;
import cn.zhku.domain.Registration;

@Repository("patientMessageDao")
public class PatientMessageDaoImpl extends BaseDaoImpl<Registration> implements PatientMessageDao {
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public Integer getTotalCount(DetachedCriteria dc) {
		//查询记录的总数
		dc.setProjection(Projections.rowCount());
		List list = null;
		try {
			list = getHibernateTemplate().findByCriteria(dc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//service类调用同一个dc查询了两次，第一个dc调用这个方法设置了查询记录数，那么需要清除这个查询条件
		//以便下一次调用dc查询list
		dc.setProjection(null);
		if (list!=null&&list.size()>0) {
			Long count = (Long) list.get(0);
			return count.intValue();
		}
		return null;
	}

	@Override
	public List getRegistrationAndDiseaseTypeSPageList(
			DetachedCriteria dc, int start, Integer pageSize) {
		//
		List list = getHibernateTemplate().findByCriteria(dc, start, pageSize);
		List<Map<String, String>> list2 = new ArrayList<>();
		for(int i = 0 ; i<list.size();i++) {
			Map map = new HashMap<>();
			Object[] object = (Object[]) list.get(i);
			Date dis_time = ((DiseaseType)object[0]).getDis_time();
			String dis_doctor = ((DiseaseType)object[0]).getDis_doctor();
			String reg_patientName = ((Registration)object[1]).getReg_patientName();
			Boolean reg_sex = ((Registration)object[1]).getReg_sex();
			Integer reg_age = ((Registration)object[1]).getReg_age();
			String reg_card = ((Registration)object[1]).getReg_card();
			Date reg_date = ((Registration)object[1]).getReg_date();
			Integer reg_serialNumber = ((Registration)object[1]).getReg_serialNumber();
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
			map.put("dis_time", sDateFormat.format(dis_time));
			map.put("dis_doctor", dis_doctor);
			map.put("reg_patientName", reg_patientName);
			map.put("reg_sex", reg_sex?'男':'女');
			map.put("reg_age", String.valueOf(reg_age));
			map.put("reg_card", reg_card);
			map.put("reg_date", sDateFormat.format(reg_date));
			map.put("reg_serialNumber", String.valueOf(reg_serialNumber));
			list2.add(map);
		}
		return list2;
	}

}
