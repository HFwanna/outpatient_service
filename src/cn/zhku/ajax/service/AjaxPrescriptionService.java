package cn.zhku.ajax.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.DiseaseType;
import cn.zhku.domain.DrugMessage;
import cn.zhku.domain.Prescription;
import cn.zhku.utils.PageBean;

public interface AjaxPrescriptionService {

	PageBean getPageBean(DetachedCriteria dc, Integer rows,
			Integer page);

	void deleteByIdList(List<Long> deleteIdList);

	void saveOrUpdate(List<Prescription> prelist);


	List<DrugMessage> getDrugMessage(DetachedCriteria dCriteria);

	Long getDrugIdByDrugName(String name);

	String getDrugMessageByDrugName(String content);


}
