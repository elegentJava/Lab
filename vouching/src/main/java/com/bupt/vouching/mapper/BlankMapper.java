package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

import com.bupt.vouching.bean.Blank;

/**
 * 填空题数据层接口
 * 
 * @author Hogan
 * 
 */
public interface BlankMapper {

	/**
	 * 随机查询指定数目和难度的填空题
	 * 
	 * @param map
	 * @return
	 */
	public List<Integer> findBlankIdByRandom(Map<String, Integer> map);

	/**
	 * 通过ID集合查询指定填空题
	 * 
	 * @param blankIds
	 * @return
	 */
	public List<Blank> findBlanksByIds(List<Integer> blankIds);

	/**
	 * 通过章节和等级查询指定填空题
	 * 
	 * @param map
	 * @return
	 */
	public List<Blank> findBlanksByChapterAndLevel(Map<String, Object> map);

	/**
	 * 随机查询指定数目和难度以及章节的填空题
	 * 
	 * @param map
	 * @return
	 */
	public List<Blank> findBlanksByRandom(Map<String, Integer> map);

}
