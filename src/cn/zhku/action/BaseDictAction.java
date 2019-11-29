package cn.zhku.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import cn.zhku.domain.BaseDict;
import cn.zhku.service.BaseDictService;

@Controller("baseDictAction")
@Scope("prototype")
public class BaseDictAction extends ActionSupport{
	@Resource(name="baseDictService")
	private BaseDictService bds;
	
	public void setBds(BaseDictService bds) {
		this.bds = bds;
	}
	
	private String dict_type_code;
	public String getDict_type_code() {
		return dict_type_code;
	}
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}


	@Override
	public String execute() throws Exception {
		System.out.println(dict_type_code);
		//璋冪敤service鏂规硶鍘昏幏鍙栧埌鍚庡彴鏁版嵁锛岃繑鍥炵被鍨嬫槸list
		 List<BaseDict> list = bds.getListByTypeCode(dict_type_code);
		 System.out.println(list==null);
		//灏唋ist鎵撳寘鎴恎son
			Gson gson = new Gson();
			//鐒跺悗瑙ｆ瀽鑾峰緱鐨刲ist锛屾妸json杞崲鎴恠tring
			String str = gson.toJson(list);
			System.out.println(str);
		//灏唃son鏁版嵁浠ュ瓧绗︿覆杈撳嚭鍒版祻瑙堝櫒
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(str);
		//涓嶉渶瑕佽浆鍙戝鐞�
		return null;
		
	}
	
}
