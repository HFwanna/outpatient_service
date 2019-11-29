package cn.zhku.ajax;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

import cn.zhku.ajax.service.AjaxDiagnoseService;

@Controller("ajaxDiagnose")
@Scope("prototype")
public class AjaxDiagnose extends ActionSupport {
	private Long reg_id;
	public Long getReg_id() {
		return reg_id;
	}
	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
	}
	
	@Resource(name="ajaxDiagnoseService")
	private AjaxDiagnoseService ads;
	public void setAds(AjaxDiagnoseService ads) {
		this.ads = ads;
	}
	public String list() throws Exception{
		System.out.println("start");//TODO
		//调用service查询整个诊断书的相关内容
		Map m =  ads.getDiagnoseMessageById(reg_id);
		
		String json = JSON.toJSONString(m);
		ServletActionContext.getResponse().setContentType("application/json;charset=utf8");
		ServletActionContext.getResponse().getWriter().write(json);
		System.out.println("AjaxDiagnose下的list方法的json内容打印"+json);
		System.out.println("the end");//TODO
		return null;
		
	}
	
}
