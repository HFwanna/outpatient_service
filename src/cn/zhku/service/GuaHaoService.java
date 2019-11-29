package cn.zhku.service;

import cn.zhku.domain.Registration;

public interface GuaHaoService {

	void save(Registration registration);

	Object[] search(Registration registration);

	Integer getMaxNumInDb();

}
