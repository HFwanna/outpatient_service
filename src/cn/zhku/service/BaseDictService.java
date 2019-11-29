package cn.zhku.service;

import java.util.List;

import cn.zhku.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> getListByTypeCode(String dict_type_code);

}
