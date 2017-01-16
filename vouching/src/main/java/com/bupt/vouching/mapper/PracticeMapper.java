package com.bupt.vouching.mapper;

import java.util.List;

import com.bupt.vouching.bean.Practice;

/**
 * 练习数据层接口
 * 
 * @author Hogan
 * 
 */
public interface PracticeMapper {

	/**
	 * 通过用户查询练习记录
	 * 
	 * @param userId
	 * @return
	 */
	public List<Practice> findPracticesByUserId(Integer userId);

	/**
	 * 添加一条练习记录
	 * 
	 * @param practice
	 * @return
	 */
	public Integer addPractice(Practice practice);
}
