package com.bupt.vouching.service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.MJSONObject;

/**
 * 考试平台业务层接口
 * 
 * @author Hogan
 * 
 */
public interface ExamService {

	/**
	 * 装载章节信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadChapters(JSONObject jParams);

	/**
	 * 修改章节状态
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject updateChapterStatus(JSONObject jParams);

	/**
	 * 装载自动组卷页面
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadAutoTest(JSONObject jParams);

	/**
	 * 自动生成试卷
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject autoGenerateExam(JSONObject jParams);

	/**
	 * 装载试卷设置页面数据
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadExamSetting(JSONObject jParams);

	/**
	 * 修改试卷的状态
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject updateExamStatus(JSONObject jParams);

	/**
	 * 删除试卷信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject deleteExam(JSONObject jParams);

	/**
	 * 装载预览试卷页面信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadPreview(JSONObject jParams);

	/**
	 * 试卷阅览页面删除题目
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject deleteQuestion(JSONObject jParams);

	/**
	 * 初始化装载手工组卷页面
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadManual(JSONObject jParams);

	/**
	 * 手工组卷页面查询试题
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject queryQuestions(JSONObject jParams);

	/**
	 * 装载参加考试页面
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadJoinExam(JSONObject jParams);

	/**
	 * 参加考试
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject startExam(JSONObject jParams);

	/**
	 * 装载考试页面
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadStartExam(JSONObject jParams);

	/**
	 * 保存用户考试信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject saveUserExam(JSONObject jParams);

	/**
	 * 装载用户考试成绩信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadExamRecord(JSONObject jParams);

	/**
	 * 自动组卷页面校验考试名称
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject autoValidateName(JSONObject jParams);
}
