package cn.zhku.ajax.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.ajax.dao.AjaxCmedicineDao;
import cn.zhku.ajax.dao.AjaxPrescriptionDao;
import cn.zhku.ajax.service.AjaxCmedicineService;
import cn.zhku.domain.Cmedicine;
import cn.zhku.domain.DrugMessage2;
import cn.zhku.domain.Prescription;
import cn.zhku.utils.PageBean;


@SuppressWarnings("all")
@Service("ajaxCmedicineService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class AjaxCmedicineServiceImpl implements AjaxCmedicineService {
	@Resource(name="ajaxCmedicineDao")
	private AjaxCmedicineDao acd;
	public void setAcd(AjaxCmedicineDao acd) {
		this.acd = acd;
	}



	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer rows,
			Integer page) {
		//调用dao查询总记录数
			Integer totalCount = acd.getTotalCount(dc);
			//调用PageBean的构造方法处理currentPage保证不溢出，还有把customerList和pageCount也封装进来
			PageBean pb = new PageBean(page,rows,totalCount);
			//调用dao查询cust_name的模糊查询，返回customerList
			List<Cmedicine> list = acd.getPageList(dc,pb.getStart(),pb.getPageSize());
			Iterator<Cmedicine> iterator = list.iterator();
			while (iterator.hasNext()) {
				Cmedicine cmedicine = iterator.next();
				DrugMessage2 cm_drugMessage = cmedicine.getCm_drugMessage2();
				cmedicine.setCm_drug_id(cm_drugMessage.getDrug_id());
				cmedicine.setCm_drug_price(cm_drugMessage.getDrug_price());
				cmedicine.setCm_drug_name(cm_drugMessage.getDrug_name());
				cmedicine.setCm_drug_message(cm_drugMessage.getDrug_message());
				cmedicine.setCm_drug_standard(cm_drugMessage.getDrug_standard());
			}
			for(int i =0 ; i<list.size();i++) {
				Cmedicine cmedicine = list.get(i);
				System.out.println(cmedicine.toString());
			}
			pb.setList(list);
			//返回PageBean对象
			return pb;
	}
	@Override
	public void deleteByIdList(List<Long> deleteIdList) {
		// TODO Auto-generated method stub
		acd.deleteByIdList(deleteIdList);
	}
	@Override
	public void saveOrUpdate(List<Cmedicine> cmedicines) {
		// TODO Auto-generated method stub
		acd.saveOrUpdate(cmedicines);
	}
	@Override
	public Long getDrugIdByDrugName(String name) {
		//通过药物名字搜索药物id
		return acd.getDrugIdByDrugName(name);
	}
	@Override
	public List<DrugMessage2> getDrugMessage2(DetachedCriteria dCriteria) {
		//查询药物属性
		return acd.getDrugMessage2(dCriteria);
	}
	@Override
	public String getDrugMessageByDrugName(String content) {
		return acd.getDrugMessageByDrugName(content);
	}

}
