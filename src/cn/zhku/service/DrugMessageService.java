package cn.zhku.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.utils.PageBean;

public interface DrugMessageService {

	PageBean getPageBeanByDc(DetachedCriteria dc, Integer rows, Integer page);

}
