package cn.zhku.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.zhku.domain.DiseaseType;
import cn.zhku.domain.Prescription;
import cn.zhku.domain.Registration;
import cn.zhku.service.DayMessageService;
import cn.zhku.service.PatientMessageService;
import cn.zhku.utils.PageBean;

@Controller("dayMessageAction")
@Scope("prototype")
public class DayMessageAction extends ActionSupport{
	//分页参数
	private Integer rows;
	private Integer page;
	private String dis_doctor;
	
	//起始时间和结束时间
	private Date beginDate;
	private Date endDate;
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDis_doctor() {
		return dis_doctor;
	}

	public void setDis_doctor(String dis_doctor) {
		this.dis_doctor = dis_doctor;
	}


	//spring注入service
	@Resource(name="dayMessageService")
	private DayMessageService dms;
	public void setDms(DayMessageService dms) {
		this.dms = dms;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("DayMessagaeAction的execute方法进来：");
		//接受前台的from数据
		DetachedCriteria dc = DetachedCriteria.forClass(Registration.class,"r");
		dc.createAlias("diseaseTypes", "d");
		dc.createAlias("prescription", "p");
		dc.createAlias("prescription.pre_drugMessage", "drug");
		dc.add(Restrictions.eqProperty("r.reg_id", "p.pre_registration"));
		dc.add(Restrictions.eqProperty("r.reg_id", "d.registration"));
		dc.add(Restrictions.eqProperty("drug.drug_id", "p.pre_drugMessage"));
		dc.add(Restrictions.eq("d.dis_doctor", dis_doctor));
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(this.beginDate);
		String formatB = null;
		String formatE = null;
		Date beginDate = null;
		Date endDate = null;
		if (this.beginDate!=null) {
			formatB = sdFormat.format(this.beginDate);
			beginDate = sdFormat.parse(formatB);
		}
		if (this.beginDate!=null) {
			formatE = sdFormat.format(this.endDate);
			endDate = sdFormat.parse(formatE);
		}
			/*String ragex = "\\d+年\\d+月\\d+日";
			 * String str = sdFormat.format(dt.getDis_time());
			 * Pattern r = Pattern.compile(ragex);
			 * Matcher m = r.matcher(str);
			 * if (m.find()) {
					group1 = m.group();
				}
			String dis_date_str = sdFormat.format(dt.getDis_time());
			Date dis_date = sdFormat.parse(dis_date_str);*/
			
		//调用service查询方法，封装pageBean
		PageBean pageBean = dms.getPageBeanByTwoDc(dc,rows,page,beginDate,endDate);
		//把这个map传给前台，读取其中的list和总记录数
		Map map = new HashMap();
		map.put("total", pageBean.getTotalCount());
		map.put("rows", pageBean.getList());
		//将结果集转换成json格式并且输出到浏览器
		String json = JSON.toJSONString(map);
		System.out.println("第二步json数据DayMessageAction打印："+json);
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		//回显数据pageBean
		return null;
	}
	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	
	
}
