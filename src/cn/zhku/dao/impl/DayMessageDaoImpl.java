package cn.zhku.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import cn.zhku.dao.DayMessageDao;
import cn.zhku.domain.DiseaseType;
import cn.zhku.domain.DrugMessage;
import cn.zhku.domain.Prescription;
import cn.zhku.domain.Registration;
@Repository("dayMessageDao")
public class DayMessageDaoImpl extends BaseDaoImpl<Registration> implements DayMessageDao {
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
		Double totalPrice = (double) 0;
		Double fee = (double) 0;
		//获取第一个病人流水号，如果这个医生的的记录中的病人流水号和这个不一样，要再算一个诊费
		Integer reg_no = null;
		for(int i = 0 ; i<list.size();i++) {
			Object[] object = (Object[])list.get(i);
			//DiseaseType    医生     诊断日期
			String dis_doctor = ((DiseaseType)object[2]).getDis_doctor();
			Date dis_time = ((DiseaseType)object[2]).getDis_time();
			//Registration  病人名字  流水号
			String reg_patientName = ((Registration)object[3]).getReg_patientName();
			Integer reg_serialNumber = ((Registration)object[3]).getReg_serialNumber();
			//DrugMessage 药物价格
			Double drug_price = ((DrugMessage)object[1]).getDrug_price();
			
			Map map = new HashMap<>();
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
			map.put("dis_time", sDateFormat.format(dis_time));
			map.put("dis_doctor", dis_doctor);
			map.put("reg_patientName", reg_patientName);
			map.put("reg_serialNumber", reg_serialNumber);
			map.put("drug_price", drug_price);
			map.put("doc_fee","");
			if (reg_serialNumber!=reg_no) {
				reg_no = reg_serialNumber;
				map.put("doc_fee",20);
				//下一个病人了的话，加20块钱诊费
				fee += 20;
			}
			totalPrice += drug_price;
			list2.add(map);
//			System.out.println(object);
		}
		Map map = new HashMap<>();
		map.put("doc_fee", fee);
		map.put("drug_price", totalPrice);
		map.put("totalPrice", fee+totalPrice);
		list2.add(map);
		return list2;
	}

}
