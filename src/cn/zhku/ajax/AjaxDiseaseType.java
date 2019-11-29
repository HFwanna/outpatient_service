package cn.zhku.ajax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.catalina.authenticator.SavedRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jcp.xml.dsig.internal.MacOutputStream;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.zhku.ajax.service.AjaxDiseaseTypeService;
import cn.zhku.ajax.service.AjaxRegistrationService;
import cn.zhku.domain.DiseaseType;
import cn.zhku.domain.Registration;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

//页面加载处方信息时就进行查询
@Controller("ajaxDiseaseType")
@Scope("prototype")
public class AjaxDiseaseType extends ActionSupport {
	@Resource(name="ajaxDiseaseTypeService")
	private AjaxDiseaseTypeService adts;
	//属性驱动获取参数
	private Long reg_id;
//	["arr1":{},"arr2":{}]
	//获取前台arr数组
	private String arr;
	public String getArr() {
		return arr;
	}
	public void setArr(String arr) {
		this.arr = arr;
	}
	
	//获取前台的deleteArr数组，这个是用于删除项目的
	private String deleteArr;
	public String getDeleteArr() {
		return deleteArr;
	}
	public void setDeleteArr(String deleteArr) {
		this.deleteArr = deleteArr;
	}
	
	
	public String list() throws Exception {
		System.out.println("第一步有没有访问ajaxDiseaseType_list");
		//调用service查询registration,返回List<Registration>
		
		//单独查询一个诊断表
		//List diagnose = adts.getDiagnose();
		DetachedCriteria dc = DetachedCriteria.forClass(DiseaseType.class);
		System.out.println(reg_id==null);
		if (reg_id!=null) {
			dc.add(Restrictions.eq("registration.reg_id", reg_id));
		}else {
			return null;
		}
		
		List<DiseaseType> list = adts.getDiseaseType(dc);
		//循环为list设置dis_time_s字符串
		for(int i = 0 ; i<list.size() ; i++) {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy年MM月dd日");
			//为dis_time_s设置格式化后的Date类型的dis_time
			list.get(i).setDis_time_s(sdFormat.format(list.get(i).getDis_time()));
//						System.out.println(sdFormat.format(list.get(i).getDis_time()));
//						System.out.println(list.get(i).toString());
		}
		//将结果集转换成json格式并且输出到浏览器
		String json = JSON.toJSONString(list);
		System.out.println("第二步json数据ajaxDiseaseType打印："+json);
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}
	
	
	public String save2() throws Exception {
		System.out.println("AjaxDiseaseType的action测试：看看进来没有");
		//调用service方法保存方法,传入前台arr
		//adts.saveOrUpdate(arr);
		//测试能不能把arr取到
		System.out.println(arr);
		
		// String arr = "{\'arr1\':[{\'dig1\':\'haha1\'}],\'arr2\':[{\'dig2\':\'haha2\'}]}";
		//取出dig1键值的方法
		/*Map<String,Object> map =  JSON.parseObject(arr);
		for (String obj : map.keySet()){
			String string = map.get(obj).toString();
			JSONArray array = JSON.parseArray(string);
			for(int i=0;i<array.size();i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				for(String key:jsonObject.keySet()) {
					System.out.println(key);
					System.out.println(jsonObject.get(key));
				}
			}
        } */
		
		//String arr = "[{\'arr1\':{\'dig1\':\'haha1\'},\'arr2\':{\'dig2\':\'haha2\'}}]";
		JSONArray array = JSON.parseArray(arr);
		for(int i = 0;i<array.size();i++) {
			JSONObject jObject = array.getJSONObject(i);
			for(String key:jObject.keySet()) {
				System.out.println(key);
				System.out.println(jObject.get(key));
			}
		}
		return null;
	}
	
	public String save() throws Exception{
		System.out.println("AjaxDiseaseType的action测试：看看进来没有");
		System.out.println(arr);
		
		List<DiseaseType> dislist = new LinkedList<DiseaseType>();
		JSONArray array = JSON.parseArray(arr);
		System.out.println("array:"+array);
		
		//array:[[{"dis_diagnose":"发烧"},{"dis_description":"感冒"},{"dis_doctor":"周医生"},{"dis_time":1521475861000},{"dis_id":2}],[{"dis_diagnose":"发烧"},{},{},{},{"dis_id":7}]]
		for(int i = 0;i<array.size();i++) {
			JSONArray jArray = array.getJSONArray(i);
			//新创建一个diseaseType类
			DiseaseType diseaseType = new DiseaseType();
			
			//解析得到的JSONArray对象
			for(int j = 0;j<jArray.size();j++) {
				//这里获取的是对象{"d":"1","d2":"2"}
				JSONObject jObject = jArray.getJSONObject(j);
				//循环获取键值赋值给diseaseType对象
				for(String key:jObject.keySet()) {
					//获取键值System.out.println(key);
					//获取键值的值System.out.println(jObject.get(key));
					if (key=="dis_id") {
						diseaseType.setDis_id(jObject.getLong(key));
					}else if (key=="dis_time") {
//						System.out.println(((String) jObject.get(key)).equals("2018-03-20"));
//						System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse((String) jObject.get(key)));
						diseaseType.setDis_time(new SimpleDateFormat("yyyy-MM-dd").parse((String) jObject.get(key)));
						System.out.println("game over");
					}else if (key=="dis_diagnose") {
						diseaseType.setDis_diagnose((String) jObject.get(key));
					}else if (key=="dis_doctor") {
						diseaseType.setDis_doctor((String) jObject.get(key));
					}else if(key=="dis_description"){
						diseaseType.setDis_description((String) jObject.get(key));
					}
					
				}
			}
			//如果诊断是空的话，就不需要录入了
			if (diseaseType.getDis_diagnose()!=null) {
				Registration registration = new Registration();
				registration.setReg_id(this.reg_id);
				diseaseType.setRegistration(registration);
				dislist.add(diseaseType);
			}
		}
		
		//删除操作
		System.out.println("=====================分割线=======================");
		List<Long> deleteIdList = new LinkedList<Long>();
		JSONArray jsonDeleteArr = JSON.parseArray(deleteArr);
		for(int i =0;i<jsonDeleteArr.size();i++) {
			//获取到JSONArray的keySet
			Set<String> keySet = jsonDeleteArr.getJSONObject(i).keySet();
			//因为keySet我已经知道只有一个值，直接使用next获取这个keySet
			//相当于JSONObject.getLong((String)key)
			//这样就获取到了数据键值的id，把id放入list中
			deleteIdList.add(jsonDeleteArr.getJSONObject(i).getLong(keySet.iterator().next()));
		}
		//删除方法
		adts.deleteByIdList(deleteIdList);
		//调用service方法执行保存或则更新操作
		adts.saveOrUpdate(dislist);
		return null;
	}

	public Long getReg_id() {
		return reg_id;
	}

	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
	}

	public void setAdts(AjaxDiseaseTypeService adts) {
		this.adts = adts;
	}



	
	
}
