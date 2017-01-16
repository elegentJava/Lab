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
import com.bupt.vouching.service.CompetitionService;

/**
 * 竞技控制层实现
 * 
 * @author Hogan
 *
 */
@Controller
@RequestMapping("/competition")
public class CompetitionController extends BaseController {

	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private CompetitionService competitionService;
	
	/**
	 * 装载排行榜信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadRank")
	public @ResponseBody JSONObject loadRank(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = competitionService.loadRank(rt.getJParams());
		} catch (Exception e) {
			log.error("装载排行榜信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 竞技匹配
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("matching")
	public @ResponseBody JSONObject matching(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = competitionService.matching(rt.getJParams());
		} catch (Exception e) {
			log.error("竞技匹配失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
}
