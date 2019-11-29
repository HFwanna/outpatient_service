package cn.zhku.ajax.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.ajax.dao.AjaxDiseaseTypeDao;
import cn.zhku.ajax.dao.AjaxRegistrationDao;
import cn.zhku.ajax.service.AjaxDiseaseTypeService;
import cn.zhku.domain.DiseaseType;
import cn.zhku.domain.Registration;

@SuppressWarnings("all")
@Service("ajaxDiseaseTypeService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class AjaxDieseaseTypeServiceImpl implements AjaxDiseaseTypeService {
	@Resource(name="ajaxDiseaseTypeDao")
	private AjaxDiseaseTypeDao adtd;
	
	
	public void setAdtd(AjaxDiseaseTypeDao adtd) {
		this.adtd = adtd;
	}


	@Override
	public List<DiseaseType> getDiseaseType(DetachedCriteria dc) {
		// 查询diseaseType表的list
		return adtd.getDiseaseType(dc);
	}


	@Override
	public void saveOrUpdate(List<DiseaseType> dislist) {
		//调用dao层方法保存或则更新
		adtd.saveOrUpdate(dislist);
		
	}


	@Override
	public void deleteByIdList(List<Long> deleteIdList) {
		// 调用dao层deleteByIdList方法批量删除数据库中的诊断信息
		adtd.deleteByIdList(deleteIdList);
	}


	@Override
	public List getDiagnose() {
		return adtd.getDiagnose();
	}

}
