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

	/**
	 * 装载班级列表
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadClassList(JSONObject jParams);

	/**
	 * 装载邮件详情信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadEmailDetail(JSONObject jParams);

	/**
	 * 批量删除邮件
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject batchDeleteEmail(JSONObject jParams);

	/**
	 * 首页装载未读站内信
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadUnreadEmailsForMain(JSONObject jParams);

}
