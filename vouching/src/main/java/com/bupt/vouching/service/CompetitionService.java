package com.bupt.vouching.service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.MJSONObject;

/**
 * 竞技业务层接口
 * 
 * @author Hogan
 * 
 */
public interface CompetitionService {

	/**
	 * 装载排行榜信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadRank(JSONObject jParams);

	/**
	 * 竞技匹配
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject matching(JSONObject jParams);

	/**
	 * 加入匹配队列
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject joinCompetition(JSONObject jParams);

	/**
	 * 装载竞技试题
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadCompetitionExam(JSONObject jParams);

	/**
	 * 查看答案并记录
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject showAnswer(JSONObject jParams);

	/**
	 * 积分处理失败
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject creditHandle(JSONObject jParams);

	/**
	 * 清除竞技信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject removeCompetitionInfo(JSONObject jParams);

	/**
	 * 装载竞技记录信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadCompetitionRecord(JSONObject jParams);

}
