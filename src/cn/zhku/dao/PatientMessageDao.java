package cn.zhku.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.Customer;
import cn.zhku.domain.Registration;

public interface PatientMessageDao extends BaseDao<Registration> {

	Integer getTotalCount(DetachedCriteria dc);

	List getRegistrationAndDiseaseTypeSPageList(DetachedCriteria dc,
			int start, Integer pageSize);

}
