package cn.zhku.ajax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.dao.BaseDao;
import cn.zhku.domain.DiseaseType;

public interface AjaxDiseaseTypeDao extends BaseDao<DiseaseType> {

	List<DiseaseType> getDiseaseType(DetachedCriteria dc);

	void saveOrUpdate(List<DiseaseType> dislist);

	void deleteByIdList(List<Long> deleteIdList);

	List getDiagnose();

}
