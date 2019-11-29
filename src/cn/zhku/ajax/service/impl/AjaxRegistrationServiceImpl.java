package cn.zhku.ajax.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.ajax.dao.AjaxRegistrationDao;
import cn.zhku.ajax.service.AjaxRegistrationService;
import cn.zhku.domain.Registration;

@SuppressWarnings("all")
@Service("ajaxRegistrationService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class AjaxRegistrationServiceImpl implements AjaxRegistrationService {
	@Resource(name="ajaxRegistrationDao")
	private AjaxRegistrationDao ard;
	
	public void setArd(AjaxRegistrationDao ard) {
		this.ard = ard;
	}
	
	@Override
	public List<Registration> getRegistration(DetachedCriteria dc) {
		return (List<Registration>) ard.getRegistration(dc);
	}
}
