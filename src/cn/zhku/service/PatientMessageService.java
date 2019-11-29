package cn.zhku.service;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.Registration;
import cn.zhku.utils.PageBean;

public interface PatientMessageService {

	PageBean getPageBeanByTwoDc(DetachedCriteria dc, Registration registration,
			Integer rows, Integer page, Date beginDate, Date endDate);
	
}
