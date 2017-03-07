package com.bupt.vouching.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.MJSONObject;

/**
 * 资源平台业务接口
 * 
 * @author Hogan
 * 
 */
public interface ResourceService {

	/**
	 * 装载函电类型树
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadCategoryTree(JSONObject jParams);

	/**
	 * 装载词汇来源
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadGlossarySource(JSONObject jParams);

	/**
	 * 查询指定词汇
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject queryGlossary(JSONObject jParams);

	/**
	 * 装载语句类型树
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadSentenceTree(JSONObject jParams);

	/**
	 * 查询语句
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject querySentence(JSONObject jParams);

	/**
	 * 加载语句查询结果
	 * 
	 * @param params
	 * @param request
	 * @return
	 */
	public HttpServletRequest loadSentence(Map<String, Object> params,
			HttpServletRequest request);

	/**
	 * 装载语句的类型和等级
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadSentenceLevelAndType(JSONObject jParams);

	/**
	 * 装载下载列表或上传列表信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadFilePage(JSONObject jParams);

	/**
	 * 上传教学资源
	 * 
	 * @param params
	 * @param request
	 * @return
	 */
	public MJSONObject uploadResource(Map<String, Object> params,
			HttpServletRequest request);

	/**
	 * 发送函电
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject sendHD(JSONObject jParams);

	/**
	 * 装载函电
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadHD(JSONObject jParams);

	/**
	 * replaceChange
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject replaceChange(JSONObject jParams);

}
