package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

import com.bupt.vouching.bean.Cloze;

/**
 * 完型填空题数据层接口
 * 
 * @author Hogan
 * 
 */
public interface ClozeMapper {

	/**
	 * 随机查询指定数目和难度的完型填空题
	 * 
	 * @param map
	 * @return
	 */
	public List<Integer> findClozeIdByRandom(Map<String, Integer> map);

	/**
	 * 根据ID集合查询指定完型试题
	 * 
	 * @param clozeIds
	 * @return
	 */
	public List<Cloze> findClozesByIds(List<Integer> clozeIds);

	/**
	 * 通过章节和等级查询完型题目
	 * 
	 * @param map
	 * @return
	 */
	public List<Cloze> findClozesByChapterAndLevel(Map<String, Object> map);

	/**
	 * 随机查询指定数目和难度以及章节的完型填空题
	 * 
	 * @param map
	 * @return
	 */
	public List<Cloze> finClozesByRandom(Map<String, Integer> map);
	
}
