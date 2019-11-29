package cn.zhku.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.crypto.interfaces.PBEKey;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.dao.DrugMessageDao;
import cn.zhku.service.DrugMessageService;
import cn.zhku.utils.PageBean;
@Service("drugMessageService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class DrugMessageServiceImpl implements DrugMessageService {
	@Resource(name="drugMessageDao")
	private DrugMessageDao dmd;
	public void setDmd(DrugMessageDao dmd) {
		this.dmd = dmd;
	}


	@Override
	public PageBean getPageBeanByDc(DetachedCriteria dc, Integer rows,
			Integer page) {
		//通过查询dc获取总记录数
		Integer totalCount = dmd.getTotalCount(dc);
		//通过参数page计算出当前页的起始索引
		PageBean pb = new PageBean(page,rows,totalCount);
		//调用dao方法通过参数page和索引起始位置查询list
		List list = dmd.getDrugMessageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		return pb;
	}

}
