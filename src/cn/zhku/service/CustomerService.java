package cn.zhku.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.Customer;
import cn.zhku.utils.PageBean;

public interface CustomerService {

	PageBean getPageBean(DetachedCriteria dc, Integer pageCount,
			Integer currentPage);

	void saveOrUpdate(Customer customer);

	Customer getCustomerByCustId(Long cust_id);

	List<Object[]> getIndustryCount();
	
}
