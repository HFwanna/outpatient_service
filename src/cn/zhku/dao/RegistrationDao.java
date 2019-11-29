package cn.zhku.dao;

import java.util.List;

import cn.zhku.domain.Registration;

public interface RegistrationDao extends BaseDao<Registration> {

	List<Registration> updateRegistrationById(Long reg_id);


}
