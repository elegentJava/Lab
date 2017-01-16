package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

import com.bupt.vouching.bean.Translate;

/**
 * 语句翻译题数据层接口
 * 
 * @author Hogan
 * 
 */
public interface TranslateMapper {

	/**
	 * 随机查询指定数目和难度的语句翻译题
	 * 
	 * @param map
	 * @return
	 */
	public List<Integer> findTranslateIdByRandom(Map<String, Integer> map);

	/**
	 * 根据ID集合查询指定语句翻译题
	 * 
	 * @param translateIds
	 * @return
	 */
	public List<Translate> findTranslatesByIds(List<Integer> translateIds);

	/**
	 * 通过章节和等级查询语句翻译题目
	 * 
	 * @param map
	 * @return
	 */
	public List<Translate> findTranslatesByChapterAndLevel(Map<String, Object> map);

	/**
	 * 随机查询指定数目和难度以及章节的语句翻译题
	 * 
	 * @param map
	 * @return
	 */
	public List<Translate> findTranslatesByRandom(Map<String, Integer> map);

}
