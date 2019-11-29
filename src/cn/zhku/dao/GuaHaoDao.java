package cn.zhku.dao;

import cn.zhku.domain.Registration;

public interface GuaHaoDao extends BaseDao<Registration> {

	void save(Registration registration);

	Object[] getByProperty(Registration registration);

	Integer getMaxNumInDb();

}
