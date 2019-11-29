package cn.zhku.ajax.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.ajax.dao.AjaxPrescriptionDao;
import cn.zhku.ajax.service.AjaxPrescriptionService;
import cn.zhku.domain.DiseaseType;
import cn.zhku.domain.DrugMessage;
import cn.zhku.domain.Prescription;
import cn.zhku.domain.User;
import cn.zhku.utils.PageBean;

@SuppressWarnings("all")
@Service("ajaxPrescriptionService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class AjaxPrescriptionServiceImpl implements AjaxPrescriptionService {
	@Resource(name="ajaxPrescriptionDao")
	private AjaxPrescriptionDao apd;
	public AjaxPrescriptionDao getApd() {
		return apd;
	}
	public void setApd(AjaxPrescriptionDao apd) {
		this.apd = apd;
	}


	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer rows,
			Integer page) {
		//调用dao查询总记录数
		Integer totalCount = apd.getTotalCount(dc);
		//调用PageBean的构造方法处理currentPage保证不溢出，还有把customerList和pageCount也封装进来
		PageBean pb = new PageBean(page,rows,totalCount);
		//调用dao查询cust_name的模糊查询，返回customerList
		List<Prescription> list = apd.getPageList(dc,pb.getStart(),pb.getPageSize());
		Iterator<Prescription> iterator = list.iterator();
		while (iterator.hasNext()) {
			//由于有引用的类，需要重新封装prescription的属性便于前台获取显示
			Prescription prescription = iterator.next();
			DrugMessage pre_drugMessage = prescription.getPre_drugMessage();
			prescription.setPre_drug_id(pre_drugMessage.getDrug_id());
			prescription.setPre_drug_price(pre_drugMessage.getDrug_price());
			prescription.setPre_drug_name(pre_drugMessage.getDrug_name());
			prescription.setPre_drug_message(pre_drugMessage.getDrug_message());
			prescription.setPre_drug_standard(pre_drugMessage.getDrug_standard());
		}
		/*for(int i =0 ; i<list.size();i++) {
			Prescription prescription = list.get(i);
			System.out.println(prescription.toString());
		}*/
		pb.setList(list);
		//返回PageBean对象
		return pb;
	}
	@Override
	public void deleteByIdList(List<Long> deleteIdList) {
		// TODO Auto-generated method stub
		apd.deleteByIdList(deleteIdList);
	}
	@Override
	public void saveOrUpdate(List<Prescription> prelist) {
		// TODO Auto-generated method stub
		apd.saveOrUpdate(prelist);
	}
	@Override
	public List<DrugMessage> getDrugMessage(DetachedCriteria dCriteria) {
		
		return apd.getDrugMessage(dCriteria);
	}
	@Override
	public Long getDrugIdByDrugName(String name) {
		//通过药物名字搜索药物id
		return apd.getDrugIdByDrugName(name);
	}
	@Override
	public String getDrugMessageByDrugName(String content) {
		//通过药物名字搜索药物信息
		return apd.getDrugMessageByDrugName(content);
	}

}
