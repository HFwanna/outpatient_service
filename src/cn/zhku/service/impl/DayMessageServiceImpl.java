package cn.zhku.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.dao.DayMessageDao;
import cn.zhku.dao.PatientMessageDao;
import cn.zhku.service.DayMessageService;
import cn.zhku.utils.PageBean;
@Service("dayMessageService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class DayMessageServiceImpl implements DayMessageService {
	//注入dao
	@Resource(name="dayMessageDao")
	private DayMessageDao dmd;
	public void setDmd(DayMessageDao dmd) {
		this.dmd = dmd;
	}
	
	@Override
	public PageBean getPageBeanByTwoDc(DetachedCriteria dc, Integer rows,
			Integer page, Date beginDate, Date endDate) {
		if (beginDate!=null&&endDate!=null) {
			dc.add(Restrictions.between("d.dis_time", beginDate, endDate));
		}
		//调用dao查询总记录数
		Integer totalCount = dmd.getTotalCount(dc);
		//调用PageBean的构造方法处理currentPage保证不溢出，还有把customerList和pageCount也封装进来
		PageBean pb = new PageBean(page,rows,totalCount);
		//调用dao查询cust_name的模糊查询，返回customerList
		List list = dmd.getRegistrationAndDiseaseTypeSPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		//返回PageBean对象
		return pb;
	}

}
