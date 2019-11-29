package cn.zhku.action;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.zhku.domain.Registration;
import cn.zhku.service.RegistrationService;

@Controller("registrationAction")
@Scope("prototype")
public class RegistrationAction extends ActionSupport implements ModelDriven<Registration> {
	//spring中bean容器
	@Resource(name="registrationService")
	private RegistrationService regs;
	
	
	//模型驱动
	private Registration registration = new Registration();
	
	//点击接诊时修改state和显示病人信息
	public String regmessage() throws Exception {
		//调用service查询病人挂号信息
		Registration registration = regs.getRegistrationById(this.registration.getReg_id());
		//执行更新操作
		regs.updateRegistrationById(this.registration.getReg_id());
		//把结果集放入ActionContext中
		ActionContext.getContext().put("registration", registration);
		//返回到index.jsp中
		return "index";
	}
	
	//执行保存相关和病人有关信息操作
	public String saveOrUpdate() throws Exception {
		//根据整个Registration对象,调用service更新或者保存操作
		try {
			regs.saveOrUpdate(registration);
		} catch (Exception e) {
			//更新或则保存失败显示相关报错异常
			throw new RuntimeException("更新或保存失败，请与系统管理员联系！");
		}
		return "index";
	}
	
	//这个方法暂时废了
	public String edit() {
		//调用service修改
		List<Registration> list = regs.updateRegistrationById(registration.getReg_id());
		//把结果集放入actionContext
		ActionContext.getContext().put("list", list);
		//返回index.jsp
		return "index";
	}
	
	//service类set方法
	public void setRegs(RegistrationService regs) {
		this.regs = regs;
	}

	@Override
	public Registration getModel() {
		return registration;
	}
	
	
	
}
