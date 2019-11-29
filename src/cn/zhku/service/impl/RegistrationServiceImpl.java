package cn.zhku.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.dao.RegistrationDao;
import cn.zhku.domain.Registration;
import cn.zhku.service.RegistrationService;
@Service("registrationService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class RegistrationServiceImpl implements RegistrationService {
	@Resource(name="registrationDao")
	private RegistrationDao regd;

	@Override
	public Registration getRegistrationById(Long id) {
		//调用dao根据id获取Registration对象
		Registration registration = regd.getById(id);
		//return对象
		return registration;
	}

	@Override
	public List<Registration> updateRegistrationById(Long reg_id) {
		return regd.updateRegistrationById(reg_id);
	}
	
	public void setRegd(RegistrationDao regd) {
		this.regd = regd;
	}

	@Override
	public void saveOrUpdate(Registration registration) {
		//调用dao层的saveOrUpdate方法
		regd.saveOrUpdate(registration);
	}

	
	
}
