package cn.zhku.service;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.utils.PageBean;

public interface DayMessageService {

	PageBean getPageBeanByTwoDc(DetachedCriteria dc, Integer rows, Integer page,
			Date beginDate, Date endDate);

}
