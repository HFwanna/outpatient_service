package cn.zhku.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.Customer;
import cn.zhku.domain.LinkMan;

public interface LinkManDao {

	void save(LinkMan linkMan);

	List<LinkMan> getLinkManPageList(DetachedCriteria dc, int start,
			Integer pageSize);

	Integer getTotalCount(DetachedCriteria dc);

	LinkMan getById(Long lkm_cust_id);

	void saveOrUpdate(LinkMan linkMan);


}
