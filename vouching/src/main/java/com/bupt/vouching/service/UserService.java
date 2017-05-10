package com.bupt.vouching.service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.MJSONObject;

/**
 * 用户业务层接口
 * 
 * @author Hogan
 * 
 */
public interface UserService {

	/**
	 * 用户注销
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject logout(JSONObject jParams);

	/**
	 * 修改用户密码
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject modifyPW(JSONObject jParams);

	/**
	 * 登录校验
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loginValidate(JSONObject jParams);

	/**
	 * 装载导航栏信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadNavigate(JSONObject jParams);

}
