package cn.zhku.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface DayMessageDao {


	List getRegistrationAndDiseaseTypeSPageList(DetachedCriteria dc, int start,
			Integer pageSize);

	Integer getTotalCount(DetachedCriteria dc);

}
