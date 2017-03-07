package com.bupt.vouching.mapper;

import com.bupt.vouching.bean.Correspondence;

/**
 * 函电数据层接口
 * 
 * @author Hogan
 * 
 */
public interface CorrespondenceMapper {

	/**
	 * 通过种类ID查询函电
	 * 
	 * @param categoryId
	 * @return
	 */
	Correspondence findCorrespondenceByCategoryId(Integer categoryId);

}
