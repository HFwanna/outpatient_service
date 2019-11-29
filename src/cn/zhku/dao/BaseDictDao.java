package cn.zhku.dao;

import java.util.List;

import cn.zhku.domain.BaseDict;

public interface BaseDictDao {

	List<BaseDict> getListByTypeCode(String dict_type_code);

}
