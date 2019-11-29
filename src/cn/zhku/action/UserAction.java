package cn.zhku.action;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.zhku.domain.Doctor;
import cn.zhku.domain.User;
import cn.zhku.service.UserService;
import cn.zhku.utils.PageBean;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<Doctor> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Doctor user = new Doctor();
	//接受权限属性
	private String occupation;
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Resource(name="userService")
	UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String login() throws Exception {
		if (occupation.equals("医生")) {
			Doctor u  = userService.getByUsername(user);
			ActionContext.getContext().getSession().put("user", u);
			return SUCCESS;
		}else if(occupation.equals("挂号员")){
			Doctor u  = userService.getByUsername(user);
			ActionContext.getContext().getSession().put("user", u);
			return "GuaHaoJsp";
		}
		return ERROR;
	}
	
	public String regist() throws Exception{
		if (user.getDoc_name()==null||user.getDoc_name().length()==0) {
			ActionContext.getContext().put("error","用户名不能为空" );
			return "registerr";
		}
		//调用service保存注册用户
		try {
			userService.saveUsercode(user);
		} catch (Exception e) {
			ActionContext.getContext().put("error", e.getMessage());
			return "registerr";
		}
		//重定向到登录页面
		System.out.println("注册成功，回调登录页面");
		return "toLogin";
	}
	
	private Integer page;
	private Integer rows;
	public String list() {
		//这里是获取查询条件
		Long user_id = user.getDoc_id();
		//获取离线对象
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		//调用service的getPageBean方法返回一个pBean对象
		PageBean pB = userService.getPageBean(dc,rows,page);
		Map map = new HashMap();
		map.put("total", pB.getTotalCount());
		map.put("rows", pB.getList());
		
		//使用fastjson工具类,使用JsonArray工具并且是用lazy加载会报错，因为JsonArray是使用反射机制工作的
			String json = JSON.toJSONString(map);
		
		ServletActionContext.getResponse().setContentType("application/json;charset=utf8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//转发Customer_list页面
		return null;
	}
	
	//easyUI的修改方法
	public String toEdit(){
		//调用service的dao的baseDao的getById方法返回一个user对象
		Doctor u = userService.getById(user.getDoc_id());
		//禁止密码回显
		u.setDoc_password("");
		
		//需要返回json数据
		String json = JSON.toJSONString(u);
		
		ServletActionContext.getResponse().setContentType("application/json;charset=utf8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//easyUI的删除
		public String delete(){
			//调用service的dao的baseDao的getById方法返回一个user对象
			@SuppressWarnings("unused")
			User u = userService.deleteById(user.getDoc_id());
			
			return null;
		}
	

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public Doctor getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
}
