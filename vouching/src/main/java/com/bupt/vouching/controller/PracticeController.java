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
import com.bupt.vouching.service.PracticeService;

/**
 * 练习平台控制层实现
 * 
 * @author Hogan
 *
 */
@Controller
@RequestMapping("/practice")
public class PracticeController extends BaseController {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private PracticeService practiceService;
	
	/**
	 * 装载练习试题选择页面
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadTestSelect")
	public @ResponseBody JSONObject loadTestSelect(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = practiceService.loadTestSelect(rt.getJParams());
		} catch (Exception e) {
			log.error("装载练习试题选择页面失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 生成练习试卷
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("generateTest")
	public @ResponseBody JSONObject generateTest(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = practiceService.generateTest(rt.getJParams());
		} catch (Exception e) {
			log.error("生成练习试卷失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载开始考试页面
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadStartTest")
	public @ResponseBody JSONObject loadStartTest(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = practiceService.loadStartTest(rt.getJParams());
		} catch (Exception e) {
			log.error("装载开始考试页面失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载练习记录
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadTestRecord")
	public @ResponseBody JSONObject loadTestRecord(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = practiceService.loadTestRecord(rt.getJParams());
		} catch (Exception e) {
			log.error("装载练习记录失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 展示答案和分数
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("showAnswerAndScore")
	public @ResponseBody JSONObject showAnswerAndScore(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = practiceService.showAnswerAndScore(rt.getJParams());
		} catch (Exception e) {
			log.error("展示答案和分数失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
