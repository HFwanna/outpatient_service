package cn.zhku.ajax.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.DiseaseType;

public interface AjaxDiseaseTypeService {

	List<DiseaseType> getDiseaseType(DetachedCriteria dc);

	void saveOrUpdate(List<DiseaseType> dislist);

	void deleteByIdList(List<Long> deleteIdList);

	List getDiagnose();

}
