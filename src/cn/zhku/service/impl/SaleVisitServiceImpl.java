package cn.zhku.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.dao.SaleVisitDao;
import cn.zhku.domain.Customer;
import cn.zhku.domain.SaleVisit;
import cn.zhku.service.SaleVisitService;
import cn.zhku.utils.PageBean;
@Service("saleVisitService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class SaleVisitServiceImpl implements SaleVisitService {
	@Resource(name="saleVisitDao")
	private SaleVisitDao svd;
	@Override
	public void saveOrUpdate(SaleVisit saleVisit) {
		svd.saveOrUpdate(saleVisit);
	}
	
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer pageSize,
			Integer currentPage) {
		//调用dao查询总记录数
		Integer totalCount = svd.getTotalCount(dc);
		//调用PageBean的构造方法处理currentPage保证不溢出，还有把customerList和pageCount也封装进来
		PageBean pb = new PageBean(currentPage,pageSize,totalCount);
		//调用dao查询cust_name的模糊查询，返回customerList
		List<SaleVisit> list = svd.getSaleVisitPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		//返回PageBean对象
		return pb;
	}
	
	public void setSvd(SaleVisitDao svd) {
		this.svd = svd;
	}

	@Override
	public SaleVisit getById(String visit_id) {
		return svd.getById(visit_id);
	}
	
	
}
