package cn.zhku.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.dao.BaseDictDao;
import cn.zhku.domain.BaseDict;
import cn.zhku.service.BaseDictService;
@Service("baseDictService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class BaseDictServiceImpl implements BaseDictService {
	@Resource(name="baseDictDao")
	private BaseDictDao bdd;
	
	public void setBdd(BaseDictDao bdd) {
		this.bdd = bdd;
	}

	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		//调用dao层的getList方法获取记录返回类型为List
		List<BaseDict> list = bdd.getListByTypeCode(dict_type_code);
		//测试异常，配置struts跳转到login.jsp
//		if(true) {
//			throw new RuntimeException("查询出错了");
//		};
		return list;
	}

}
