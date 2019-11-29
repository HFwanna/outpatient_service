package cn.zhku.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{

	Integer getTotalCount(DetachedCriteria dc);

	List<Customer> getCustomerPageList(DetachedCriteria dc, int start,
			Integer pageSize);

	void saveOrUpdate(Customer customer);

	List<Object[]> getIndustryCount();

}
