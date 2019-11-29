package cn.zhku.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.domain.LinkMan;
import cn.zhku.utils.PageBean;

@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public interface LinkManService {

	void save(LinkMan linkMan);

	PageBean getPageBean(DetachedCriteria dc, Integer pageSize,
			Integer currentPage);

	LinkMan getById(Long lkm_cust_id);

	void saveOrUpdate(LinkMan linkMan);

}
