package com.bupt.vouching.mapper;

import java.util.List;

import com.bupt.vouching.bean.CorrespondenceCategory;

/**
 * 函电类型数据层接口
 * 
 * @author Hogan
 * 
 */
public interface CorrespondenceCategoryMapper {

	/**
	 * 查找所有函电类型
	 * 
	 * @return
	 */
	public List<CorrespondenceCategory> findAllCategories();
}
