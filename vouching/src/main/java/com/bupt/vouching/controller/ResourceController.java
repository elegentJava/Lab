package com.bupt.vouching.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.bupt.vouching.service.ResourceService;

/**
 * 资源平台控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/resource/")
public class ResourceController extends BaseController {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private ResourceService resourceService;
	
	/**
	 * 装载函电类型树
	 * 
	 * @param jo
	 * @param request
	 * @return
	 */
	@RequestMapping("loadCategoryTree")
	public @ResponseBody JSONObject loadCategoryTree(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.loadCategoryTree(rt.getJParams());
		} catch (Exception e) {
			log.error("装载函电类型树失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载词汇来源
	 * 
	 * @param jo
	 * @param request
	 * @return
	 */
	@RequestMapping("loadGlossarySource")
	public @ResponseBody JSONObject loadGlossarySource(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.loadGlossarySource(rt.getJParams());
		} catch (Exception e) {
			log.error("装载词汇来源失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 查询指定词汇
	 * 
	 * @param jo
	 * @param request
	 * @return
	 */
	@RequestMapping("queryGlossary")
	public @ResponseBody JSONObject queryGlossary(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.queryGlossary(rt.getJParams());
		} catch (Exception e) {
			log.error("查询指定词汇失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载语句类型树
	 * 
	 * @param jo
	 * @param request
	 * @return
	 */
	@RequestMapping("loadSentenceTree")
	public @ResponseBody JSONObject loadSentenceTree(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.loadSentenceTree(rt.getJParams());
		} catch (Exception e) {
			log.error("装载语句类型树失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载语句的类型和等级
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadSentenceLevelAndType")
	public @ResponseBody JSONObject loadSentenceLevelAndType(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.loadSentenceLevelAndType(rt.getJParams());
		} catch (Exception e) {
			log.error("装载语句的类型和等级失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 查询语句
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("querySentence")
	public @ResponseBody JSONObject querySentence(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.querySentence(rt.getJParams());
		} catch (Exception e) {
			log.error("装载语句的类型和等级失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载下载列表信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadFilePage")
	public @ResponseBody JSONObject loadFilePage(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.loadFilePage(rt.getJParams());
		} catch (Exception e) {
			log.error("装载下载列表信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 上传教学资源
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/uploadResource")
	public @ResponseBody JSONObject uploadResource(HttpServletRequest request, HttpServletResponse response) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(request);
			detail = resourceService.uploadResource(rt.getParams(),request);
		} catch (Exception e) {
			log.error("上传教学资源失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
}
