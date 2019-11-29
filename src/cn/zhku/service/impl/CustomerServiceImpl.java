package cn.zhku.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.ws.util.xml.CDATA;

import cn.zhku.dao.CustomerDao;
import cn.zhku.domain.Customer;
import cn.zhku.service.CustomerService;
import cn.zhku.utils.PageBean;
@Service("customerService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class CustomerServiceImpl implements CustomerService {
	@Resource(name="customerDao")
	private CustomerDao cd ;
	
	
	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}


	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer pageSize,
			Integer currentPage) {
		//调用dao查询总记录数
		Integer totalCount = cd.getTotalCount(dc);
		//调用PageBean的构造方法处理currentPage保证不溢出，还有把customerList和pageCount也封装进来
		PageBean pb = new PageBean(currentPage,pageSize,totalCount);
		//调用dao查询cust_name的模糊查询，返回customerList
		List<Customer> list = cd.getCustomerPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		//返回PageBean对象
		return pb;
	}


	@Override
	public void saveOrUpdate(Customer customer) {
		cd.saveOrUpdate(customer);
		
	}


	@Override
	public Customer getCustomerByCustId(Long cust_id) {
		return (Customer) cd.getById(cust_id);
	}


	@Override
	public List<Object[]> getIndustryCount() {
		List<Object[]> list = cd.getIndustryCount();
		return list;
	}



}
