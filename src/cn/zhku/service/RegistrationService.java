package cn.zhku.service;

import java.util.List;

import cn.zhku.domain.Registration;

public interface RegistrationService {

	Registration getRegistrationById(Long reg_id);

	List<Registration> updateRegistrationById(Long reg_id);

	void saveOrUpdate(Registration registration);

}
