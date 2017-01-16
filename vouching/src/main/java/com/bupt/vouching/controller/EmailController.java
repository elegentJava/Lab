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
import com.bupt.vouching.service.EmailService;

/**
 * 邮件控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/email/")
public class EmailController extends BaseController {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private EmailService emailService;
	
	/**
	 * 装载邮件信箱
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadEmailBox")
	public @ResponseBody JSONObject loadEmailBox(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = emailService.loadEmailBox(rt.getJParams());
		} catch (Exception e) {
			log.error("装载邮件信箱失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载联系人列表
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadUsersInClass")
	public @ResponseBody JSONObject loadUsersInClass(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = emailService.loadUsersInClass(rt.getJParams());
		} catch (Exception e) {
			log.error("装载邮件信箱失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("sendEmail")
	public @ResponseBody JSONObject sendEmail(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = emailService.sendEmail(rt.getJParams());
		} catch (Exception e) {
			log.error("发送邮件失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
}