package cn.zhku.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface DrugMessageDao {

	Integer getTotalCount(DetachedCriteria dc);

	List getDrugMessageList(DetachedCriteria dc, int start, Integer rows);

}
