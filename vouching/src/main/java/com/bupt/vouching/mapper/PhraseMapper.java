package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

import com.bupt.vouching.bean.Phrase;

/**
 * 名词解释题数据层接口
 * 
 * @author Hogan
 * 
 */
public interface PhraseMapper {

	/**
	 * 随机查询指定数目和难度的名词解释题
	 * 
	 * @param map
	 * @return
	 */
	public List<Integer> findPhraseIdByRandom(Map<String, Integer> map);

	/**
	 * 根据ID集合查询名词解释题
	 * 
	 * @param phraseIds
	 * @return
	 */
	public List<Phrase> findPhrasesByIds(List<Integer> phraseIds);

	/**
	 * 通过章节和等级查询名词解释题目
	 * 
	 * @param map
	 * @return
	 */
	public List<Phrase> findPhrasesByChapterAndLevel(Map<String, Object> map);

	/**
	 * 随机查询指定数目和难度以及章节的名词解释题
	 * 
	 * @param map
	 * @return
	 */
	public List<Phrase> findPhrasesByRandom(Map<String, Integer> map);

}
