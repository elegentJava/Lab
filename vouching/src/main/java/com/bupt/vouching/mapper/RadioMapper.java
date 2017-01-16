package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

import com.bupt.vouching.bean.Radio;

/**
 * 单选题数据层接口
 * 
 * @author Hogan
 * 
 */
public interface RadioMapper {

	/**
	 * 随机查询指定数目和难度的单选题
	 * 
	 * @param map
	 * @return
	 */
	public List<Integer> findRadioIdByRandom(Map<String, Integer> map);

	/**
	 * 通过ID集合查询指定单选题
	 * 
	 * @param radioIds
	 * @return
	 */
	public List<Radio> findRadiosByIds(List<Integer> radioIds);

	/**
	 * 通过章节和等级查询单选题目
	 * 
	 * @param map
	 * @return
	 */
	public List<Radio> findRadiosByChapterAndLevel(Map<String, Object> map);
	
	/**
	 * 随机查询指定数目和难度以及章节的单选题 
	 * 
	 * @param map
	 * @return
	 */
	public List<Radio> findRadiosByRandom(Map<String, Integer> map);
}
