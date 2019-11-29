package cn.zhku.ajax.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.domain.Cmedicine;
import cn.zhku.domain.DrugMessage;
import cn.zhku.domain.DrugMessage2;
import cn.zhku.domain.Prescription;
import cn.zhku.utils.PageBean;

public interface AjaxCmedicineService {

	PageBean getPageBean(DetachedCriteria dc, Integer rows, Integer page);

	void deleteByIdList(List<Long> deleteIdList);

	void saveOrUpdate(List<Cmedicine> cmedicines);

	Long getDrugIdByDrugName(String name);

	List<DrugMessage2> getDrugMessage2(DetachedCriteria dCriteria);

	String getDrugMessageByDrugName(String content);


}
