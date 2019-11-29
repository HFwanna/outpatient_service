package cn.zhku.ajax.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.ajax.dao.ajaxDiagnoseDao;
import cn.zhku.ajax.service.AjaxDiagnoseService;

@SuppressWarnings("all")
@Service("ajaxDiagnoseService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class AjaxDiagnoseServiceImpl implements AjaxDiagnoseService {
	@Resource(name="ajaxDiagnoseDao")
	private ajaxDiagnoseDao add;
	public void setAdd(ajaxDiagnoseDao add) {
		this.add = add;
	}


	@Override
	public Map getDiagnoseMessageById(Long reg_id) {
		//调用dao层查询多个表相关内容
		List l2 = add.getDiagnoseMessageById(reg_id);
		List l = add.getRegistrationById(reg_id);
		List l3 = add.getPrescriptionById(reg_id);
		//提取需要回显的内容
		Map<String, List<Map<String, String>>> bigMap = new HashMap<>();
		//封装进Map对象中
		bigMap.put("first", l);
		bigMap.put("second", l2);
		bigMap.put("third", l3);
		return bigMap ;
	}
	
}
