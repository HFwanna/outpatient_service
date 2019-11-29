package cn.zhku.ajax.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.Registration;

public interface AjaxRegistrationService {



	List<Registration> getRegistration(DetachedCriteria dc);
	
}
