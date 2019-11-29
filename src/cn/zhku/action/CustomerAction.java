package cn.zhku.action;



import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.zhku.domain.Customer;
import cn.zhku.service.CustomerService;
import cn.zhku.utils.PageBean;
@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	//书写从前台接收的参数封装进对象
	private Customer customer = new Customer();
	private Integer pageSize;
	private Integer currentPage;
	private File photo;
	
	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
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

	//spring中获取service，还要在spring中配置bean，
	@Resource(name="customerService")
	private CustomerService cs;
	
	public void setCs(CustomerService cs) {
		this.cs = cs;
	}
	
	//根据客户行业查询客户数量
	public String industryCount() {
		//调用service方法查询客户行业下的客户数量，返回List<Object[]>
		List<Object[]> list = cs.getIndustryCount();
		//把list结果集放入到ActionContext中
		ActionContext.getContext().put("list", list);
		//返回到industryCount.jsp中
		return "industryCount";
	}

	public String list() {
		//这里是获取查询条件
		String cust_name = customer.getCust_name();
		//获取离线对象
		System.out.println("cust_name:"+cust_name);
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		if (cust_name!=null&&cust_name.length()>0) {
			dc.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		//调用service的getPageBean方法返回一个pBean对象
		PageBean pB = cs.getPageBean(dc,pageSize,currentPage);
		//把对象放进request域中
		ActionContext.getContext().put("pBean", pB);
		
		//转发Customer_list页面
		return "list";
	}
	
	public String add() {
		if(photo!=null) {
			photo.renameTo(new File("d:/newFile.jsp"));
		}
		//调用service执行保存操作
		cs.saveOrUpdate(customer);
		//保存完毕重定向到CustomerAction_list
		return "redirectToCustomerAction";
	}
	
	public String edit() {
		//调用service的dao的baseDao的getById方法返回一个customer对象
		customer = cs.getCustomerByCustId(customer.getCust_id());
		System.out.println(customer.getCust_id());
		//用上面方法是把返回customer对象放回模型驱动model中，即在StackValue中的Root（栈）中，
		//所以在jsp页面要取值用model.cust_id这样的方式取，而下面这种方法是将返回customer对象
		//放到ActionContext（相当于ognl的context）中，jsp页面通过#customer.cust_id可以取出
		Customer c = cs.getCustomerByCustId(customer.getCust_id());
		ActionContext.getContext().put("customer", c);
		return "toEdit";
	}

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	
	
	
	
}
