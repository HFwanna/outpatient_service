package cn.zhku.action;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xpath.internal.operations.Bool;

import cn.zhku.domain.DrugMessage;
import cn.zhku.domain.DrugMessage2;
import cn.zhku.service.DrugMessageService;
import cn.zhku.utils.PageBean;

@Controller("drugMessageAction")
@Scope("prototype")
public class DrugMessageAction extends ActionSupport{
	private DrugMessage drugMessage;
	//单独一个中药西药的类型参数
	private String drugType;
	//价格区间
	private String beginPrice;
	private String endPrice;

	//分页参数
	private Integer rows;
	private Integer page;
	

	//spring注入service
	@Resource(name="drugMessageService")
	private DrugMessageService dms;
	public void setDms(DrugMessageService dms) {
		this.dms = dms;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("DrugMessageAction的execute方法进来：");
		//正则验证输入的价格是否为数字
		String regex = "\\d+";
		boolean matches = Pattern.matches(regex, beginPrice);
		boolean matches2 = Pattern.matches(regex, endPrice);
		DetachedCriteria dc = null;
		if (drugType.equals("西药")) {
			 dc = DetachedCriteria.forClass(DrugMessage.class);
			if (drugMessage!=null && drugMessage.getDrug_name()!=null && !drugMessage.getDrug_name().equals("")) {
				dc.add(Restrictions.like("drug_name", drugMessage.getDrug_name()));
			}
			if (drugMessage!=null && drugMessage.getDrug_type()!=null) {
				dc.add(Restrictions.eq("drug_type", drugMessage.getDrug_type()));
			}
			if (matches && matches2) {
				Double beginPrice = Double.valueOf(this.beginPrice);
				Double endPrice = Double.valueOf(this.endPrice);
				dc.add(Restrictions.between("drug_price", beginPrice, endPrice));
			}
		}else if (drugType.equals("中药")) {
			dc = DetachedCriteria.forClass(DrugMessage2.class);
			if (drugMessage!=null && drugMessage.getDrug_name()!=null && !drugMessage.getDrug_name().equals("")) {
				dc.add(Restrictions.like("drug_name", drugMessage.getDrug_name()));
			}
			if (drugMessage!=null && drugMessage.getDrug_type()!=null ) {
				dc.add(Restrictions.eq("drug_type", drugMessage.getDrug_type()));
			}
			if (matches && matches2) {
				Integer beginPrice = Integer.valueOf(this.beginPrice);
				Integer endPrice = Integer.valueOf(this.endPrice);
				dc.add(Restrictions.between("drug_price", beginPrice, endPrice));
			}
		}
		//调用service查询方法，封装pageBean
		PageBean pageBean = dms.getPageBeanByDc(dc,rows,page);
		//把这个map传给前台，读取其中的list和总记录数
		Map map = new HashMap();
		map.put("total", pageBean.getTotalCount());
		map.put("rows", pageBean.getList());
		//将结果集转换成json格式并且输出到浏览器
		String json = JSON.toJSONString(map);
		System.out.println("第二步json数据DrugMessageAction打印："+json);
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
	public DrugMessage getDrugMessage() {
		return drugMessage;
	}

	public void setDrugMessage(DrugMessage drugMessage) {
		this.drugMessage = drugMessage;
	}


	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getBeginPrice() {
		return beginPrice;
	}

	public void setBeginPrice(String beginPrice) {
		this.beginPrice = beginPrice;
	}

	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}

	
	
	
}
