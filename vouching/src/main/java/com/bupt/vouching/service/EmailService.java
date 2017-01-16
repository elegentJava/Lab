package com.bupt.vouching.service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.MJSONObject;

/**
 * 邮件业务层接口
 * 
 * @author Hogan
 * 
 */
public interface EmailService {

	/**
	 * 装载邮件信箱
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadEmailBox(JSONObject jParams);

	/**
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadUsersInClass(JSONObject jParams);

	/**
	 * 发送邮件
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject sendEmail(JSONObject jParams);

}
