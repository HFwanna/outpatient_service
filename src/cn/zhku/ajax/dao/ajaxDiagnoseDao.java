package cn.zhku.ajax.dao;

import java.util.List;

import cn.zhku.dao.BaseDao;

public interface ajaxDiagnoseDao extends BaseDao<Object>{

	List getDiagnoseMessageById(Long reg_id);

	List getRegistrationById(Long reg_id);

	List getPrescriptionById(Long reg_id);
	
}
