package com.bupt.vouching.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.BaseController;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.frame.RequestTemplate;
import com.bupt.vouching.frame.ResponseTemplate;
import com.bupt.vouching.service.UserService;

/**
 * 用户控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController{

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private UserService userService;
	
	/**
	 * 登录验证
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("login")
	public @ResponseBody JSONObject login(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.loginValidate(rt.getJParams());
		} catch (Exception e) {
			log.error("登录验证失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 修改用户密码
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("modifyPW")
	public @ResponseBody JSONObject modifyPW(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.modifyPW(rt.getJParams());
		} catch (Exception e) {
			log.error("修改用户密码失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 注销登录
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("logout")
	public @ResponseBody JSONObject logout(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.logout(rt.getJParams());
		} catch (Exception e) {
			log.error("注销登录失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
}
