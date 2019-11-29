package cn.zhku.ajax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.dao.BaseDao;
import cn.zhku.domain.DrugMessage;
import cn.zhku.domain.Prescription;
import cn.zhku.domain.User;

public interface AjaxPrescriptionDao extends BaseDao<Prescription> {

	void deleteByIdList(List<Long> deleteIdList);

	void saveOrUpdate(List<Prescription> prelist);

	List<DrugMessage> getDrugMessage(DetachedCriteria dCriteria);

	Long getDrugIdByDrugName(String name);

	String getDrugMessageByDrugName(String content);

}
