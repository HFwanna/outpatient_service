package cn.zhku.ajax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.zhku.dao.BaseDao;
import cn.zhku.domain.Registration;

public interface AjaxRegistrationDao extends BaseDao<Registration> {


	List<Registration> getRegistration(DetachedCriteria dc);

}
