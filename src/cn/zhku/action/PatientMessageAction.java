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
import cn.zhku.domain.Registration;
import cn.zhku.service.PatientMessageService;
import cn.zhku.utils.PageBean;

@Controller("patientMessageAction")
@Scope("prototype")
public class PatientMessageAction extends ActionSupport implements ModelDriven<Registration>{
	private Registration registration = new Registration();
	
	//分页参数
	private Integer rows;
	private Integer page;
	
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

	//spring注入service
	@Resource(name="patientMessageService")
	private PatientMessageService pms;
	public void setPms(PatientMessageService pms) {
		this.pms = pms;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("PatientMessageAction的execute方法进来：");
		Set<DiseaseType> diseaseTypes = registration.getDiseaseTypes();
		//接受前台的from数据
		DetachedCriteria dc = DetachedCriteria.forClass(Registration.class,"r");
		dc.createAlias("diseaseTypes", "d");
		dc.add(Restrictions.eqProperty("r.reg_id", "d.registration"));
		Iterator<DiseaseType> iterator = diseaseTypes.iterator();
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
		while (iterator.hasNext()) {
			DiseaseType dt =  iterator.next();
			/*String ragex = "\\d+年\\d+月\\d+日";
			 * String str = sdFormat.format(dt.getDis_time());
			 * Pattern r = Pattern.compile(ragex);
			 * Matcher m = r.matcher(str);
			 * if (m.find()) {
					group1 = m.group();
				}
			String dis_date_str = sdFormat.format(dt.getDis_time());
			Date dis_date = sdFormat.parse(dis_date_str);*/
			dc.add(Restrictions.like("d.dis_doctor", "%"+dt.getDis_doctor()+"%"));
		}
		//调用service查询方法，封装pageBean
		PageBean pageBean = pms.getPageBeanByTwoDc(dc,registration,rows,page,beginDate,endDate);
		//把这个map传给前台，读取其中的list和总记录数
		Map map = new HashMap();
		map.put("total", pageBean.getTotalCount());
		map.put("rows", pageBean.getList());
		//将结果集转换成json格式并且输出到浏览器
		String json = JSON.toJSONString(map);
		System.out.println("第二步json数据PatientMessageAction打印："+json);
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

	@Override
	public Registration getModel() {
		//
		return registration;
	}

	
	
}
