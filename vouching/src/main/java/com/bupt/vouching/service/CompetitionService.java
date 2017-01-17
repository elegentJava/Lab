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

}
