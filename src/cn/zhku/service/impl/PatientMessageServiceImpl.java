package cn.zhku.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.zhku.dao.PatientMessageDao;
import cn.zhku.domain.Registration;
import cn.zhku.service.PatientMessageService;
import cn.zhku.utils.PageBean;
@Service("patientMessageService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class PatientMessageServiceImpl implements PatientMessageService {
	//注入dao
	@Resource(name="patientMessageDao")
	private PatientMessageDao pmd;
	public void setPmd(PatientMessageDao pmd) {
		this.pmd = pmd;
	}


	@Override
	public PageBean getPageBeanByTwoDc(DetachedCriteria dc,
		Registration registration,Integer rows, Integer page,Date beginDate,Date endDate) {
		//Boolean bl = (registration.getReg_sex().equals("男"))?true:false;
		if (registration.getReg_date()!=null) {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				dc.add(Restrictions.eq("reg_date", sdFormat.parse(sdFormat.format(registration.getReg_date()))));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(registration.getReg_patientName()!=null) {
			dc.add(Restrictions.like("reg_patientName", "%"+registration.getReg_patientName()+"%"));
		}
		if(registration.getReg_age()!=null) {
			dc.add(Restrictions.eq("reg_age", registration.getReg_age()));
		}
		if (registration.getReg_card()!=null) {
			dc.add(Restrictions.like("reg_card", "%"+registration.getReg_card()+"%"));
		}
		if (registration.getReg_serialNumber()!=null) {
			dc.add(Restrictions.eq("reg_serialNumber",registration.getReg_serialNumber()));
		}
		if (beginDate!=null&&endDate!=null) {
			dc.add(Restrictions.between("d.dis_time", beginDate, endDate));
		}
		//调用dao查询总记录数
		Integer totalCount = pmd.getTotalCount(dc);
		//调用PageBean的构造方法处理currentPage保证不溢出，还有把customerList和pageCount也封装进来
		PageBean pb = new PageBean(page,rows,totalCount);
		//调用dao查询cust_name的模糊查询，返回customerList
		List list = pmd.getRegistrationAndDiseaseTypeSPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		//返回PageBean对象
		return pb;
	}

}
