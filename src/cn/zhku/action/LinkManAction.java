package cn.zhku.action;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.zhku.domain.Customer;
import cn.zhku.domain.LinkMan;
import cn.zhku.service.CustomerService;
import cn.zhku.service.LinkManService;
import cn.zhku.utils.PageBean;
@Controller("linkManAction")
@Scope("prototype")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	//注入spring的cs
	@Resource(name="linkManService")
	private LinkManService lms;
	//struts获取的对象
	private LinkMan linkMan = new LinkMan();
	
	private Integer pageSize;
	private Integer currentPage;
	
	public String add() throws Exception {
		//调用service执行保存操作
		lms.saveOrUpdate(linkMan);
		//转发到linkMan的list.jsp
		return "toList";
	}
	
	public String edit() {
		//接受页面传来的linkman.lkm_id
		LinkMan lm = lms.getById(linkMan.getLkm_id());
		ActionContext.getContext().put("linkMan", lm);
		//转发到add.jsp
		return "edit";
	}
	
	public String list() {
		//这里是获取查询条件
		String lkm_name = linkMan.getLkm_name();
		//获取离线对象
		System.out.println("Lkm_name:"+lkm_name);
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		//模糊查询
		if (lkm_name!=null&&lkm_name.length()>0) {
			dc.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
		}
		//客户id查询
		if (linkMan.getCustomer()!=null&&linkMan.getCustomer().getCust_id()!=null) {
			dc.add(Restrictions.eq("customer.cust_id",linkMan.getCustomer().getCust_id()));
		}
		
		//调用service的getPageBean方法返回一个pBean对象
		PageBean pB = lms.getPageBean(dc,pageSize,currentPage);
		//把对象放进request域中
		ActionContext.getContext().put("pBean", pB);
		
		//转发LinkMan_list页面
		return "list";
	}

	public void setLms(LinkManService lms) {
		this.lms = lms;
	}

	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	
}
