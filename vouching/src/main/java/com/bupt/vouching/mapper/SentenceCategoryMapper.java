package com.bupt.vouching.mapper;

import java.util.List;

import com.bupt.vouching.bean.SentenceCategory;

/**
 * 语句类型数据类接口
 * 
 * @author Hogan
 * 
 */
public interface SentenceCategoryMapper {

	/**
	 * 查找所有语句类型
	 * 
	 * @return
	 */
	public List<SentenceCategory> findAllCategories();
}
