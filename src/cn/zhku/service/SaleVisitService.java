package cn.zhku.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.SaleVisit;
import cn.zhku.utils.PageBean;

public interface SaleVisitService {

	void saveOrUpdate(SaleVisit saleVisit);

	PageBean getPageBean(DetachedCriteria dc, Integer pageSize,
			Integer currentPage);

	SaleVisit getById(String visit_id);


}
