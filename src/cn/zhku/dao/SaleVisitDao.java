package cn.zhku.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.Customer;
import cn.zhku.domain.SaleVisit;

public interface SaleVisitDao {

	void saveOrUpdate(SaleVisit saleVisit);

	Integer getTotalCount(DetachedCriteria dc);

	List<SaleVisit> getSaleVisitPageList(DetachedCriteria dc, int start,
			Integer pageSize);

	SaleVisit getById(String visit_id);

}
