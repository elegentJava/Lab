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
import com.bupt.vouching.service.ExamService;

/**
 * 考试平台控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {

	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private ExamService examService;
	
	/**
	 * 装载章节信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadChapters")
	public @ResponseBody JSONObject loadChapters(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadChapters(rt.getJParams());
		} catch (Exception e) {
			log.error("装载章节信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 修改章节状态
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("updateChapterStatus")
	public @ResponseBody JSONObject updateChapterStatus(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.updateChapterStatus(rt.getJParams());
		} catch (Exception e) {
			log.error("修改章节状态失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 初始化装载手工组卷页面
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadManual")
	public @ResponseBody JSONObject loadManual(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadManual(rt.getJParams());
		} catch (Exception e) {
			log.error("初始化装载手工组卷页面失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 手工组卷页面查询试题
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("queryQuestions")
	public @ResponseBody JSONObject queryQuestions(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.queryQuestions(rt.getJParams());
		} catch (Exception e) {
			log.error("手工组卷页面查询试题失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载自动组卷页面
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadAutoTest")
	public @ResponseBody JSONObject loadAutoTest(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadAutoTest(rt.getJParams());
		} catch (Exception e) {
			log.error("装载自动组卷页面失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 自动生成试卷
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("autoGenerateExam")
	public @ResponseBody JSONObject autoGenerateExam(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.autoGenerateExam(rt.getJParams());
		} catch (Exception e) {
			log.error("自动生成试卷失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载试卷设置页面数据
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadExamSetting")
	public @ResponseBody JSONObject loadExamSetting(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadExamSetting(rt.getJParams());
		} catch (Exception e) {
			log.error("装载试卷设置页面数据失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 修改试卷的状态
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("updateExamStatus")
	public @ResponseBody JSONObject updateExamStatus(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.updateExamStatus(rt.getJParams());
		} catch (Exception e) {
			log.error("修改试卷的状态失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 删除试卷信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("deleteExam")
	public @ResponseBody JSONObject deleteExam(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.deleteExam(rt.getJParams());
		} catch (Exception e) {
			log.error("删除试卷信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载预览试卷页面信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadPreview")
	public @ResponseBody JSONObject loadPreview(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadPreview(rt.getJParams());
		} catch (Exception e) {
			log.error("装载预览试卷页面信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 试卷阅览页面删除题目
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("deleteQuestion")
	public @ResponseBody JSONObject deleteQuestion(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.deleteQuestion(rt.getJParams());
		} catch (Exception e) {
			log.error("试卷阅览页面删除题目失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载参加考试页面
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadJoinExam")
	public @ResponseBody JSONObject loadJoinExam(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadJoinExam(rt.getJParams());
		} catch (Exception e) {
			log.error("装载参加考试页面失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 参加考试
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("startExam")
	public @ResponseBody JSONObject startExam(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.startExam(rt.getJParams());
		} catch (Exception e) {
			log.error("参加考试失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载考试页面
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadStartExam")
	public @ResponseBody JSONObject loadStartExam(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadStartExam(rt.getJParams());
		} catch (Exception e) {
			log.error("装载考试页面失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 保存用户考试信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("saveUserExam")
	public @ResponseBody JSONObject saveUserExam(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.saveUserExam(rt.getJParams());
		} catch (Exception e) {
			log.error("保存用户考试信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载用户考试成绩信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadExamRecord")
	public @ResponseBody JSONObject loadExamRecord(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadExamRecord(rt.getJParams());
		} catch (Exception e) {
			log.error("装载用户考试成绩信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 校验考试名称
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("validateExamName")
	public @ResponseBody JSONObject validateExamName(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.validateExamName(rt.getJParams());
		} catch (Exception e) {
			log.error("校验考试名称失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	 
	/**
	 * 手动创建试卷
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("manualSaveExam")
	public @ResponseBody JSONObject manualSaveExam(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.manualSaveExam(rt.getJParams());
		} catch (Exception e) {
			log.error("手动创建试卷失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载批改试卷信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadMarkPaper")
	public @ResponseBody JSONObject loadMarkPaper(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadMarkPaper(rt.getJParams());
		} catch (Exception e) {
			log.error("装载批改试卷信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载批阅试卷详情信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadMarkDetail")
	public @ResponseBody JSONObject loadMarkDetail(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadMarkDetail(rt.getJParams());
		} catch (Exception e) {
			log.error("装载批阅试卷详情信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 批阅试卷
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("markPaper")
	public @ResponseBody JSONObject markPaper(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.markPaper(rt.getJParams());
		} catch (Exception e) {
			log.error("批阅试卷失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载考试信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadGradeExam")
	public @ResponseBody JSONObject loadGradeExam(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadGradeExam(rt.getJParams());
		} catch (Exception e) {
			log.error("装载考试信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载成绩信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadExamGrade")
	public @ResponseBody JSONObject loadExamGrade(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = examService.loadExamGrade(rt.getJParams());
		} catch (Exception e) {
			log.error("装载成绩信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
}
