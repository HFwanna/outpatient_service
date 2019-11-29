package cn.zhku.ajax;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.zhku.ajax.service.AjaxRegistrationService;
import cn.zhku.domain.Customer;
import cn.zhku.domain.Registration;

//页面加载就进行查询
@Controller("ajaxRegistration")
@Scope("prototype")
public class AjaxRegistration extends ActionSupport {
	@Resource(name="ajaxRegistrationService")
	private AjaxRegistrationService ars;
	//属性驱动获取参数
	private Long reg_id;
	
	public String list() throws Exception {
		System.out.println("第一步有没有访问AjaxRegistration");
		//调用service查询registration,返回List<Registration>
		DetachedCriteria dc = DetachedCriteria.forClass(Registration.class);
		List<Registration> list = ars.getRegistration(dc);
		//将结果集转换成json格式并且输出到浏览器  [{},{},{}]
		String json = JSON.toJSONString(list);
		System.out.println("第二步json数据打印："+json);
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}
	
	
	public void setArs(AjaxRegistrationService ars) {
		this.ars = ars;
	}
	public Long getReg_id() {
		return reg_id;
	}
	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
	}
	
}

