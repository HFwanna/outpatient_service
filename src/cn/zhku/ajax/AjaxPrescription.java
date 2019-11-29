package cn.zhku.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import cn.zhku.ajax.service.AjaxCmedicineService;
import cn.zhku.ajax.service.AjaxPrescriptionService;
import cn.zhku.domain.DiseaseType;
import cn.zhku.domain.DrugMessage;
import cn.zhku.domain.Prescription;
import cn.zhku.domain.Registration;
import cn.zhku.utils.PageBean;

@SuppressWarnings("all")
@Controller("ajaxPrescription")
@Scope("prototype")
public class AjaxPrescription extends ActionSupport {
	//前台传来的行数
	private Integer rows;
	//前台传来的currentPage
	private Integer page;
	//这是单个变量，可以这样接受，而pre_drug_id有很多个，这样无法接受
	private Long reg_id;
	
	
	@Resource(name="ajaxPrescriptionService")
	private AjaxPrescriptionService aps;
	public void setAps(AjaxPrescriptionService aps) {
		this.aps = aps;
	}
	public String list() throws Exception {
		System.out.println("第一步：AjaxPrescription下的list方法测试：");
		//获取离线对象
		DetachedCriteria dc = DetachedCriteria.forClass(Prescription.class);
		dc.add(Restrictions.eq("pre_registration.reg_id", reg_id));
		//调用service的getPageBean方法返回一个pBean对象
		PageBean pB = aps.getPageBean(dc,rows,page);
		//把这个map传给前台，读取其中的list和总记录数
		Map map = new HashMap();
		map.put("total", pB.getTotalCount());
		map.put("rows", pB.getList());
		System.out.println("AjaxPrescription_list第二步："+map.toString()+"AjaxPrescription第二步map打印结束。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		
		//使用fastjson工具类,使用JsonArray工具并且是用lazy加载会报错，因为JsonArray是使用反射机制工作的
			String json = JSON.toJSONString(map);
			System.out.println(json);
		
		ServletActionContext.getResponse().setContentType("application/json;charset=utf8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//不需要转发，位置在西药表单
		return null;
	}
	
	
	private String arr2;
	public String getArr2() {
		return arr2;
	}
	public void setArr2(String arr2) {
		this.arr2 = arr2;
	}
	//获取前台的deleteArr数组，这个是用于删除项目的
	private String deleteArr2;
	public String getDeleteArr2() {
		return deleteArr2;
	}
	public void setDeleteArr2(String deleteArr2) {
		this.deleteArr2 = deleteArr2;
	}

	public String save() throws Exception{
		System.out.println("AjaxPrescription的save测试：看看进来没有");
		System.out.println(arr2);
		
		List<Prescription> prelist = new LinkedList<Prescription>();
		JSONArray array = JSON.parseArray(arr2);
		System.out.println("array:"+array);
		
		//array:[[{"dis_diagnose":"发烧"},{"dis_description":"感冒"},{"dis_doctor":"周医生"},{"dis_time":1521475861000},{"dis_id":2}],[{"dis_diagnose":"发烧"},{},{},{},{"dis_id":7}]]
		for(int i = 0;i<array.size();i++) {
			JSONArray jArray = array.getJSONArray(i);
			//新创建一个diseaseType类
			Prescription prescription = new Prescription();
			DrugMessage drugMessage = new DrugMessage();
			
			//解析得到的JSONArray对象
			for(int j = 0;j<jArray.size();j++) {
				//这里获取的是对象{"d":"1","d2":"2"}
				JSONObject jObject = jArray.getJSONObject(j);
				//循环获取键值赋值给diseaseType对象
				for(String key:jObject.keySet()) {
					//获取键值System.out.println(key);
					//获取键值的值System.out.println(jObject.get(key));
					if (key=="pre_id") {
						prescription.setPre_id(jObject.getLong(key));
					}else if (key=="pre_drug_id") {
						//如果不为空，就设置外键值，否则不管
						if (jObject.get(key)!=null&&!jObject.get(key).equals("")) {
							drugMessage.setDrug_id(jObject.getLong(key));
							prescription.setPre_drugMessage(drugMessage);
						}
					}else if (key=="pre_drug_name") {
						try {
							//如果不为空，就设置外键值，否则不管
							if (jObject.get(key) != null
									&& !jObject.get(key).equals("")) {
								if (jObject.getString(key).matches("[0-9]+") ) {
									drugMessage.setDrug_id(jObject.getLong(key));
									prescription.setPre_drugMessage(drugMessage);
								}else {
									System.out.println(jObject.getString(key).matches("[0-9]+"));
									System.out.println(jObject.getString(key));
									Long id = getDrugIdByDrugName(jObject.getString(key));
									drugMessage.setDrug_id(id);
									prescription.setPre_drugMessage(drugMessage);
								}
								
							} 
						} catch (Exception e) {
							System.out.println("AjaxPrescription的save方法出错了");
							e.printStackTrace();
						}
					}else if (key=="pre_drug_standard") {
						if (jObject.get(key)!=null&&!jObject.get(key).equals("")) {
							drugMessage.setDrug_standard(jObject.getString(key));
							prescription.setPre_drugMessage(drugMessage);
						}
					}else if (key=="pre_mg") {
						prescription.setPre_mg((String) jObject.get(key));
					}else if(key=="pre_unit"){
						prescription.setPre_unit((String) jObject.get(key));
					}else if(key=="pre_pathway"){
						prescription.setPre_pathway((String) jObject.get(key));
					}else if(key=="pre_frequency"){
						prescription.setPre_frequency((String) jObject.get(key));
					}else if(key=="pre_days"){
						prescription.setPre_days((String) jObject.get(key));
					}else if(key=="pre_totalCount"){
						prescription.setPre_totalCount((String) jObject.get(key));
					}else if(key=="pre_drug_price"){
						if (jObject.get(key)!=null&&!jObject.getString(key).equals("")) {
							prescription.setPre_drug_price(jObject.getDoubleValue(key));
						}
					}
					
				}
			}
			//如果药物名字是空的话，就不需要录入了
			if (prescription.getPre_drugMessage().getDrug_id()!=null) {
				//设置上处方表单的参考的reg_id和drug_id
				Registration registration = new Registration();
				registration.setReg_id(this.reg_id);
				prescription.setPre_registration(registration);
				prelist.add(prescription);
			}
		}
		
		//删除操作
		System.out.println("=====================分割线=======================");
		List<Long> deleteIdList = new LinkedList<Long>();
		JSONArray jsonDeleteArr = JSON.parseArray(deleteArr2);
		for(int i =0;i<jsonDeleteArr.size();i++) {
			//获取到JSONArray的keySet
			Set<String> keySet = jsonDeleteArr.getJSONObject(i).keySet();
			//因为keySet我已经知道只有一个值，直接使用next获取这个keySet
			//相当于JSONObject.getLong((String)key)
			//这样就获取到了数据键值的id，把id放入list中
			deleteIdList.add(jsonDeleteArr.getJSONObject(i).getLong(keySet.iterator().next()));
		}
		//删除方法
		aps.deleteByIdList(deleteIdList);
		//调用service方法执行保存或则更新操作
		aps.saveOrUpdate(prelist);
		return null;
	}
	
	
	/*其中方法查询出来的list循环get出对象，用对象.getdrugMessage.get属性1，属性2,属性3,属性4
	获取值后，属性1放到一个list1里面，属性2放到一个list2（格式[{"key":"value"},{"k":"v"}]）里面....
	a.用JSON.toJSONString转换成json格式的string输出到浏览器，打印四次把四个数组输出到浏览器 
		应该不可行了
	b.再创建一个数组，把list1到4放进去，再用JSON.toJSONString转  这个数据格式是:
		[[{{"key":"value"},{"k":"v"}}],[{"key":"value"},{"k":"v"}]]
		循环 i response.length    for( j   response[i].length)    arr.push({response[i][j]})  
	
	
	返回数据形式：[[],[],[],[]]
	.done接受这4个数据，*/
	public String drugMessageList() throws Exception {
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		//查询条件封装
		DetachedCriteria dCriteria = DetachedCriteria.forClass(DrugMessage.class);
		//调用service查询整个list
		List<DrugMessage> list = aps.getDrugMessage(dCriteria);
		/*List<Object> list2 = new LinkedList<Object>();
		for (int i = 0; i < list.size(); i++) {
			//把四个属性取出来
			Long drug_id = list.get(i).getDrug_id();
			String drug_message = list.get(i).getDrug_message();
			String drug_name = list.get(i).getDrug_name();
			Double drug_price = list.get(i).getDrug_price();
			//重新封装成一个map对象，一共有list.size()个map对象
			Map<String, String> map = new HashMap<>();
			map.put("drug_id", String.valueOf(drug_id));
			map.put("drug_message", drug_message);
			map.put("drug_name", drug_name);
			map.put("drug_price", String.valueOf(drug_price));
			//把map对象封装进一个全新的list对象内，数据结构是[{"drug_id":drug_id,"drug_message":"drug_message","drug_name":drug_name,"drug_price":,drug_price},{},{}]
			list2.add(map);
		}*/
		//验证list2的数据结构
		System.out.println("***********************************************************************************");
		System.out.println(list);
		System.out.println(JSON.toJSONString(list));
		System.out.println("***********************************************************************************");
		
		ServletActionContext.getResponse().setContentType("application/json;charset=utf8");
		ServletActionContext.getResponse().getWriter().write(JSON.toJSONString(list));
		return null;
		
	}
	
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	//南区center药物信息显示方法
	public String preMessage() throws Exception{
		System.out.println("xianxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+this.content);
		//调用getDrugMessageByDrugName
		String centerDrugMessage = aps.getDrugMessageByDrugName(this.content);
		ServletActionContext.getResponse().setContentType("application/json;charset=utf8");
		ServletActionContext.getResponse().getWriter().write(centerDrugMessage);
		return null;
	}
	
	public Long getDrugIdByDrugName(String name) {
		return aps.getDrugIdByDrugName(name);
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

	public Long getReg_id() {
		return reg_id;
	}

	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
	}


	
	
	
	
}
