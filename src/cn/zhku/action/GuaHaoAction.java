package cn.zhku.action;



import java.io.File;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.catalina.ant.FindLeaksTask;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import antlr.CSharpCodeGenerator;
import cn.zhku.domain.Customer;
import cn.zhku.domain.Registration;
import cn.zhku.service.CustomerService;
import cn.zhku.service.GuaHaoService;
import cn.zhku.utils.PageBean;
import jdk.nashorn.internal.ir.Flags;
@Controller("guaHaoAction")
@Scope("prototype")
public class GuaHaoAction extends ActionSupport implements ModelDriven<Registration> {
	//书写从前台接收的参数封装进对象
	private Registration registration = new Registration();
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}


	//spring中获取service，还要在spring中配置bean
	@Resource(name="guaHaoService")
	private GuaHaoService ghs;
	
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}


	public String save() {
		if (flag.equals("1")) {
			Map map = search();
			if(map!=null) {
				ActionContext.getContext().put("registration", map);
				return "finnishFinding";
			}else {
				//什么都没查到
				return "none";
			}
		}
		//系统自动获取的变量
		Integer reg_serialNumber = ghs.getMaxNumInDb();
		Boolean reg_state = false;
		//获取当前系统时间
		long l = System.currentTimeMillis();
		Date reg_date = new Date(l);
		//获取完这3个值要设置进registration对象中
		registration.setReg_serialNumber(reg_serialNumber+1);
		registration.setReg_state(reg_state);
		registration.setReg_date(reg_date);
		
		//获取需要存储的变量
		Long reg_id = registration.getReg_id();
		String reg_patientName = registration.getReg_patientName();
		String reg_card = registration.getReg_card();
		Boolean reg_sex = registration.getReg_sex();
		Integer reg_age = registration.getReg_age();
		Long reg_phone = registration.getReg_phone();
		String reg_diseaseType = registration.getReg_diseaseType();
		
		//保存或则修改
		if(reg_card!=null&&!reg_card.equals("")) {
			ghs.save(registration);
			return "success";
		}
		return "fail";
		
	}
	
	public Map search() {
		//根据6个项，其中一项查找内容
		if (registration.getReg_diseaseType().equals("--- 请选择 ---")) {
			registration.setReg_diseaseType(null);
		}	
		if (registration.getReg_sex()==false) {
			registration.setReg_sex(null);
		}
		Object[] registration = ghs.search(this.registration);
		if (registration!=null) {
			Map map = new HashMap<>();
			map.put("reg_id", registration[0]);
			map.put("reg_patientName", registration[1]);
			map.put("reg_card", registration[2]);
			map.put("reg_sex", registration[3]);
			map.put("reg_age", registration[4]);
			map.put("reg_phone", registration[5]);
			map.put("reg_diseaseType", registration[6]);
			/*for(int i = 0 ; i<registration.length ; i++) {
				//数组Obejct[]内的 数据  “XXX=”的内容格式获取
				Pattern pattern = Pattern.compile("[a-z]*=");
				//获得matcher对象
				Matcher matcher = pattern.matcher((CharSequence) registration[i]);
				//把xxx=的等号前面内容提取出来 ,(((String)registration[i]).split("="))[0]是把 xxx= 截取xxx内容
				//因为Object[陈先生, 01, true, 18, 123, 神经科],所以不能用String key = (((String)registration[i]).split("="))[0];
				if
				//把匹配XXX=的内容替换为空
				String value = matcher.replaceAll("");
				//
				map.put(key, value);
			}*/
			return map;
		}
		
		return null;
		
	}
	

	@Override
	public Registration getModel() {
		// TODO Auto-generated method stub
		return registration;
	}

	
	
	
	
}
