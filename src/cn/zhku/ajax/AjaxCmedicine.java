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
import cn.zhku.domain.Cmedicine;
import cn.zhku.domain.DrugMessage2;
import cn.zhku.domain.Registration;
import cn.zhku.utils.PageBean;

@Controller("ajaxCmedicine")
@Scope("prototype")
public class AjaxCmedicine extends ActionSupport {
	//前台传来的行数
	private Integer rows;
	//前台传来的currentPage
	private Integer page;
	//这是单个变量，可以这样接受，而pre_drug_id有很多个，这样无法接受
	private Long reg_id;
	
	@Resource(name="ajaxCmedicineService")
	private AjaxCmedicineService acs;
	public void setAcs(AjaxCmedicineService acs) {
		this.acs = acs;
	}

	public String list() throws Exception {
		System.out.println("第一步：AjaxCmedicine下的list方法测试：");
		//获取离线对象
		DetachedCriteria dc = DetachedCriteria.forClass(Cmedicine.class);
		dc.add(Restrictions.eq("cm_registration.reg_id", reg_id));
		//调用service的getPageBean方法返回一个pBean对象
		PageBean pB = acs.getPageBean(dc,rows,page);
		//把这个map传给前台，读取其中的list和总记录数
		Map map = new HashMap();
		map.put("total", pB.getTotalCount());
		map.put("rows", pB.getList());
		System.out.println("第二步："+map.toString());
		
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
	
	private String arr3;
	public String getArr3() {
		return arr3;
	}
	public void setArr3(String arr3) {
		this.arr3 = arr3;
	}
	//获取前台的deleteArr数组，这个是用于删除项目的
	private String deleteArr3;
	public String getDeleteArr3() {
		return deleteArr3;
	}
	public void setDeleteArr3(String deleteArr3) {
		this.deleteArr3 = deleteArr3;
	}

	public String save() throws Exception{
		System.out.println("AjaxCmedicine的save测试：看看进来没有");
		System.out.println(arr3);
		
		List<Cmedicine> cmlist = new LinkedList<Cmedicine>();
		JSONArray array = JSON.parseArray(arr3);
		System.out.println("array:"+array);
		
		//array:[[{"dis_diagnose":"发烧"},{"dis_description":"感冒"},{"dis_doctor":"周医生"},{"dis_time":1521475861000},{"dis_id":2}],[{"dis_diagnose":"发烧"},{},{},{},{"dis_id":7}]]
		for(int i = 0;i<array.size();i++) {
			JSONArray jArray = array.getJSONArray(i);
			//新创建一个diseaseType类
			Cmedicine cmedicine = new Cmedicine();
			DrugMessage2 drugMessage2 = new DrugMessage2();
			
			//解析得到的JSONArray对象
			for(int j = 0;j<jArray.size();j++) {
				//这里获取的是对象{"d":"1","d2":"2"}
				JSONObject jObject = jArray.getJSONObject(j);
				//循环获取键值赋值给diseaseType对象
				for(String key:jObject.keySet()) {
					//获取键值System.out.println(key);
					//获取键值的值System.out.println(jObject.get(key));
					if (key=="cm_id") {
						cmedicine.setCm_id(jObject.getLong(key));
					}else if (key=="cm_drug_id") {
						//如果不为空，就设置外键值，否则不管
						if (jObject.get(key)!=null&&!jObject.get(key).equals("")) {
							drugMessage2.setDrug_id(jObject.getLong(key));
							cmedicine.setCm_drugMessage2(drugMessage2);
						}
					}else if (key=="cm_drug_name") {
						try {
							//如果不为空，就设置外键值，否则不管
							if (jObject.get(key) != null
									&& !jObject.get(key).equals("")) {
								if (jObject.getString(key).matches("[0-9]+") ) {
									drugMessage2.setDrug_id(jObject.getLong(key));
									cmedicine.setCm_drugMessage2(drugMessage2);
								}else {
									Long id = getDrugIdByDrugName(jObject.getString(key));
									drugMessage2.setDrug_id(id);
									cmedicine.setCm_drugMessage2(drugMessage2);
								}
								
							} 
						} catch (Exception e) {
							System.out.println("AjaxCmedicine的save方法出错了");
							e.printStackTrace();
						}
					}else if (key=="cm_drug_standard") {
						if (jObject.get(key)!=null&&!jObject.get(key).equals("")) {
							drugMessage2.setDrug_standard(jObject.getString(key));
							cmedicine.setCm_drugMessage2(drugMessage2);
						}
					}else if (key=="cm_mg") {
						cmedicine.setCm_mg((String) jObject.get(key));
					}else if(key=="cm_unit"){
						cmedicine.setCm_unit((String) jObject.get(key));
					}else if(key=="cm_pathway"){
						cmedicine.setCm_pathway((String) jObject.get(key));
					}else if(key=="cm_frequency"){
						cmedicine.setCm_frequency((String) jObject.get(key));
					}else if(key=="cm_days"){
						cmedicine.setCm_days((String) jObject.get(key));
					}else if(key=="cm_totalCount"){
						cmedicine.setCm_totalCount((String) jObject.get(key));
					}else if(key=="cm_drug_price"){
						if (jObject.get(key)!=null&&!jObject.getString(key).equals("")) {
							cmedicine.setCm_drug_price(jObject.getDoubleValue(key));
						}
					}
					
				}
			}
			//如果药物名字是空的话，就不需要录入了
			if (cmedicine.getCm_drugMessage2().getDrug_id()!=null) {
				//设置上处方表单的参考的reg_id和drug_id
				Registration registration = new Registration();
				registration.setReg_id(this.reg_id);
				cmedicine.setCm_registration(registration);
				cmlist.add(cmedicine);
			}
		}
		
		//删除操作
		System.out.println("=====================分割线=======================");
		List<Long> deleteIdList = new LinkedList<Long>();
		JSONArray jsonDeleteArr = JSON.parseArray(deleteArr3);
		for(int i =0;i<jsonDeleteArr.size();i++) {
			//获取到JSONArray的keySet
			Set<String> keySet = jsonDeleteArr.getJSONObject(i).keySet();
			//因为keySet我已经知道只有一个值，直接使用next获取这个keySet
			//相当于JSONObject.getLong((String)key)
			//这样就获取到了数据键值的id，把id放入list中
			deleteIdList.add(jsonDeleteArr.getJSONObject(i).getLong(keySet.iterator().next()));
		}
		//删除方法
		acs.deleteByIdList(deleteIdList);
		//调用service方法执行保存或则更新操作
		acs.saveOrUpdate(cmlist);
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
		DetachedCriteria dCriteria = DetachedCriteria.forClass(DrugMessage2.class);
		//调用service查询整个list
		List<DrugMessage2> list = acs.getDrugMessage2(dCriteria);
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
	public String cmMessage() throws Exception{
		//调用getDrugMessageByDrugName
		String centerDrugMessage = acs.getDrugMessageByDrugName(this.content);
		ServletActionContext.getResponse().setContentType("application/json;charset=utf8");
		ServletActionContext.getResponse().getWriter().write(centerDrugMessage);
		return null;
	}
	
	
	public Long getDrugIdByDrugName(String name) {
		return acs.getDrugIdByDrugName(name);
	}
	
	
		
	

		public Long getReg_id() {
			return reg_id;
		}

		public void setReg_id(Long reg_id) {
			this.reg_id = reg_id;
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
}
