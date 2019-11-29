package cn.zhku.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.dao.LinkManDao;
import cn.zhku.domain.Customer;
import cn.zhku.domain.LinkMan;
import cn.zhku.service.LinkManService;
import cn.zhku.utils.PageBean;
@Service("linkManService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class LinkManServiceImpl implements LinkManService {
	@Resource(name="linkManDao")
	private LinkManDao lmd;
	
	
	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}
	
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer pageSize,
			Integer currentPage) {
		//调用dao查询总记录数
		Integer totalCount = lmd.getTotalCount(dc);
		//调用PageBean的构造方法处理currentPage保证不溢出，还有把customerList和pageCount也封装进来
		PageBean pb = new PageBean(currentPage,pageSize,totalCount);
		//调用dao查询cust_name的模糊查询，返回customerList
		List<LinkMan> list = lmd.getLinkManPageList(dc,pb.getStart(),pb.getPageSize());
		System.out.println("list"+list);
		pb.setList(list);
		//返回PageBean对象
		return pb;
	}

	@Override
	public void saveOrUpdate(LinkMan linkMan) {
		lmd.saveOrUpdate(linkMan);
	}

	@Override
	public LinkMan getById(Long lkm_cust_id) {
		return lmd.getById(lkm_cust_id);
	}

	@Override
	public void save(LinkMan linkMan) {
		// TODO Auto-generated method stub
		
	}

}
