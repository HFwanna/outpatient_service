package cn.zhku.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.dao.GuaHaoDao;
import cn.zhku.domain.Registration;
import cn.zhku.service.GuaHaoService;
import cn.zhku.service.RegistrationService;
@Service("guaHaoService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class GuaHaoServiceImpl implements GuaHaoService {
	@Resource(name="GuaHaoDao")
	private GuaHaoDao ghd;
	public void setGhd(GuaHaoDao ghd) {
		this.ghd = ghd;
	}

	@Override
	public void save(Registration registration) {
		//调用用dao的save方法
		ghd.saveOrUpdate(registration);
		
	}

	@Override
	public Object[] search(Registration registration) {
		return ghd.getByProperty(registration);
	}

	@Override
	public Integer getMaxNumInDb() {
		//获取数据库中最大的流水号
		return ghd.getMaxNumInDb();
	}
	

}
