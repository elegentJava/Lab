package com.bupt.vouching.service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.MJSONObject;

/**
 * 练习平台业务接口
 * 
 * @author Hogan
 * 
 */
public interface PracticeService {

	/**
	 * 装载练习试题选择页面
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadTestSelect(JSONObject jParams);

	/**
	 * 生成练习试卷
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject generateTest(JSONObject jParams);

	/**
	 * 装载开始考试页面
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadStartTest(JSONObject jParams);

	/**
	 * 装载练习记录
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadTestRecord(JSONObject jParams);

	/**
	 * 展示答案和分数
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject showAnswerAndScore(JSONObject jParams);

}
