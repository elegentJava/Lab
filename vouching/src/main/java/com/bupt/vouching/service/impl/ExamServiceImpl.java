package com.bupt.vouching.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.bean.Blank;
import com.bupt.vouching.bean.Chapter;
import com.bupt.vouching.bean.Cloze;
import com.bupt.vouching.bean.Exam;
import com.bupt.vouching.bean.Phrase;
import com.bupt.vouching.bean.Radio;
import com.bupt.vouching.bean.Translate;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.bean.UserPaper;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.mapper.BlankMapper;
import com.bupt.vouching.mapper.ChapterMapper;
import com.bupt.vouching.mapper.ClozeMapper;
import com.bupt.vouching.mapper.ExamMapper;
import com.bupt.vouching.mapper.PhraseMapper;
import com.bupt.vouching.mapper.RadioMapper;
import com.bupt.vouching.mapper.TranslateMapper;
import com.bupt.vouching.mapper.UserPaperMapper;
import com.bupt.vouching.service.ExamService;
import com.bupt.vouching.service.bean.QuestionLevel;
import com.bupt.vouching.service.bean.QuestionType;
import com.bupt.vouching.type.LevelType;
import com.bupt.vouching.type.PageSize;
import com.bupt.vouching.type.QuestionCategory;
import com.bupt.vouching.type.error.ErrorCode;
import com.bupt.vouching.type.error.ExamError;
import com.bupt.vouching.util.Utils;
import com.bupt.vouching.util.page.PageHelper;
import com.bupt.vouching.util.page.PageInfo;

/**
 * 考试平台业务层实现
 * 
 * @author Hogan
 * 
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {

	@Resource
	private GlobalContext globalContext;
	
	@Resource
	private ChapterMapper chapterMapper;
	
	@Resource
	private ExamMapper examMapper;
	
	@Resource
	private RadioMapper radioMapper;
	
	@Resource
	private BlankMapper blankMapper;
	
	@Resource
	private ClozeMapper clozeMapper;
	
	@Resource
	private TranslateMapper translateMapper;
	
	@Resource
	private PhraseMapper phraseMapper;
	
	@Resource
	private UserPaperMapper userPaperMapper;
	
	@Override
	public MJSONObject loadChapters(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<Chapter> activeChapters = chapterMapper.findAllChaptersByActiveStatus(Consts.ACTIVE);
		List<Chapter> inactiveChapters = chapterMapper.findAllChaptersByActiveStatus(Consts.INACTIVE);
		detail.put("activeChapters", activeChapters);
		detail.put("inactiveChapters", inactiveChapters);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject updateChapterStatus(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer status = jParams.getInteger("status");
		Integer chapterId = jParams.getInteger("chapterId");
		if (status != null && chapterId != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", status);
			map.put("chapterId", chapterId);
			if (chapterMapper.updateActiveStatus(map) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(ExamError.CHAPTER_UPDATE_STATUS);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject loadManual(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<QuestionType> questionTypes = new ArrayList<QuestionType>();
		List<QuestionLevel> levels = new ArrayList<QuestionLevel>();
		List<Chapter> activeChapters = null;
		List<Radio> radios = null;
		for (QuestionCategory qc : QuestionCategory.values()) {
			questionTypes.add(new QuestionType(qc.getId(), qc.getTag(), qc.getName()));
		}
		for (LevelType e : LevelType.values()) {
			levels.add(new QuestionLevel(e.getId(), e.getDescription()));
		}
		activeChapters = chapterMapper.findAllChaptersByActiveStatus(Consts.ACTIVE);
		PageHelper.startPage(1, PageSize.MANUAL_QUESTION_RESULT);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chapterId", 1);
		map.put("level", 0);
		radios = radioMapper.findRadiosByChapterAndLevel(map);
		PageInfo<Radio> pageInfo = new PageInfo<Radio>(radios);
		detail.put("chapters", activeChapters);
		detail.put("questionTypes", questionTypes);
		detail.put("levels", levels);
		detail.put("radios", pageInfo.getList());
		result.setDetail(detail);
		result.setPageInfo(pageInfo);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public MJSONObject queryQuestions(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List questions = null;
		Integer chapterId = jParams.getInteger("chapterId");
		Integer source = jParams.getInteger("source");
		Integer level = jParams.getInteger("level");
		Integer pageNum = jParams.getInteger("pageNum");
		if (chapterId != null && source != null && level != null && pageNum != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("chapterId", chapterId);
			map.put("level", level);
			PageHelper.startPage(pageNum, PageSize.MANUAL_QUESTION_RESULT);
			switch (QuestionCategory.byId(source)) {
			case BLANK:
				questions = blankMapper.findBlanksByChapterAndLevel(map);
				break;
			case RADIO:
				questions = radioMapper.findRadiosByChapterAndLevel(map);
				break;
			case CLOZE:
				questions = clozeMapper.findClozesByChapterAndLevel(map);
				break;
			case PHRASE:
				questions = phraseMapper.findPhrasesByChapterAndLevel(map);
				break;
			case TRANSLATE:
				questions = translateMapper.findTranslatesByChapterAndLevel(map);
				break;
			default:
				result.setErrorCode(ExamError.QUESTION_CATEGORY_INVALID);
				break;
			}
			PageInfo<Object> pageInfo = new PageInfo<Object>(questions);
			detail.put("questions", pageInfo.getList());
			result.setDetail(detail);
			result.setPageInfo(pageInfo);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject loadAutoTest(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<QuestionType> questionTypes = new ArrayList<QuestionType>();
		List<QuestionLevel> levels = new ArrayList<QuestionLevel>();
		for (QuestionCategory qc : QuestionCategory.values()) {
			questionTypes.add(new QuestionType(qc.getId(), qc.getTag(), qc.getName()));
		}
		for(LevelType level : LevelType.values()){
			levels.add(new QuestionLevel(level.getId(), level.getDescription()));
		}
		detail.put("questionTypes", questionTypes);
		detail.put("levels", levels);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
	@Override
	public MJSONObject autoGenerateExam(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String token = jParams.getString("token");
		String name = jParams.getString("name");
		String bak = jParams.getString("bak");
		Integer radioCount = jParams.getInteger("radioCount");
		Integer blankCount = jParams.getInteger("blankCount");
		Integer clozeCount = jParams.getInteger("clozeCount");
		Integer phraseCount = jParams.getInteger("phraseCount");
		Integer translateCount = jParams.getInteger("translateCount");
		Integer level = jParams.getInteger("level");
		if (!Utils.isNullOrBlank(name) && radioCount != null
				&& blankCount != null && clozeCount != null
				&& phraseCount != null && translateCount != null
				&& level != null) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("level", level);
			map.put("random", radioCount);
			List<Integer> radioId = radioMapper.findRadioIdByRandom(map);
			String radioIds = generateIds(radioId);
			map.put("random", blankCount);
			List<Integer> blankId = blankMapper.findBlankIdByRandom(map);
			String blankIds = generateIds(blankId);
			map.put("random", clozeCount);
			List<Integer> clozeId = clozeMapper.findClozeIdByRandom(map);
			String clozeIds = generateIds(clozeId);
			map.put("random", phraseCount);
			List<Integer> phraseId = phraseMapper.findPhraseIdByRandom(map);
			String phraseIds = generateIds(phraseId);
			map.put("random", translateCount);
			List<Integer> translateId = translateMapper.findTranslateIdByRandom(map);
			String translateIds = generateIds(translateId);
			Exam exam = new Exam();
			exam.setBak(bak);
			exam.setName(name);
			exam.setCreateDate(new Date());
			exam.setTeacherId(globalContext.getUserToken().get(token).getUserId());
			exam.setClassId(globalContext.getUserToken().get(token).getClassId());
			exam.setIsActive(Consts.INACTIVE);
			exam.setRadioId(radioIds);
			exam.setBlankId(blankIds);
			exam.setClozeId(clozeIds);
			exam.setPhraseId(phraseIds);
			exam.setTranslateId(translateIds);
			if (examMapper.saveExam(exam) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(ExamError.ADD_EXAM_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject loadExamSetting(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		Integer pageNum = jParams.getInteger("pageNum");
		Integer isActive = jParams.getInteger("isActive");
		if (pageNum != null && isActive != null) {
			PageHelper.startPage(pageNum, PageSize.EXAM_SETTING_RESULT);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teacherId", globalContext.getUserToken().get(token).getUserId());
			map.put("isActive", isActive);
			List<Exam> exams = examMapper.findExamDetailByStatusAndTeacher(map);
			PageInfo<Exam> pageInfo = new PageInfo<Exam>(exams);
			if (isActive == Consts.ACTIVE) {
				detail.put("activeExams", pageInfo.getList());
			} else {
				detail.put("inactiveExams", pageInfo.getList());
			}
			result.setDetail(detail);
			result.setPageInfo(pageInfo);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject updateExamStatus(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer examId = jParams.getInteger("examId");
		Integer status = jParams.getInteger("status");
		if (examId != null && status != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("examId", examId);
			map.put("isActive", status);
			if (examMapper.updateExamStatusById(map) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(ExamError.EXAM_UPDATE_STATUS);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject deleteExam(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer examId = jParams.getInteger("examId");
		if (examId != null) {
			if (examMapper.deleteExamById(examId) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(ExamError.EXAM_DELETE);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject loadPreview(JSONObject jParams){
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		Integer examId = jParams.getInteger("examId");
		if (examId != null) {
			Exam exam = examMapper.findExamById(examId);
			if(exam.getRadioIds() != null){
				List<Radio> radios = radioMapper.findRadiosByIds(exam.getRadioIds());
				detail.put("radios", radios);
			} else {
				detail.put("radios", null);
			}
			if(exam.getPhraseIds() != null){
				List<Phrase> phrases = phraseMapper.findPhrasesByIds(exam.getPhraseIds());
				detail.put("phrases", phrases);
			} else {
				detail.put("phrases", null);
			}
			if(exam.getBlankIds() != null){
				List<Blank> blanks = blankMapper.findBlanksByIds(exam.getBlankIds());
				detail.put("blanks", blanks);
			} else {
				detail.put("blanks", null);
			}
			if(exam.getTranslateIds() != null){
				List<Translate> translates = translateMapper.findTranslatesByIds(exam.getTranslateIds());
				detail.put("translates", translates);
			} else {
				detail.put("translates", null);
			}
			if(exam.getClozeIds() != null){
				List<Cloze> clozes = clozeMapper.findClozesByIds(exam.getClozeIds());
				detail.put("clozes", clozes);
			} else {
				detail.put("clozes", null);
			}
			detail.put("examName", exam.getName());
			detail.put("bak", exam.getBak());
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject deleteQuestion(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer examId = jParams.getInteger("examId");
		Integer categoryId = jParams.getInteger("categoryId");
		Integer questionId = jParams.getInteger("questionId");
		if (examId != null && categoryId != null && questionId != null) {
			String updatedId = "";
			Exam exam = examMapper.findExamById(examId);
			switch (QuestionCategory.byId(categoryId)) {
				case RADIO:
					updatedId = generateUpdatedId(questionId, exam.getRadioIds());
					exam.setRadioId(updatedId);
					break;
				case BLANK:
					updatedId = generateUpdatedId(questionId, exam.getBlankIds());
					exam.setBlankId(updatedId);
					break;
				case PHRASE:
					updatedId = generateUpdatedId(questionId, exam.getPhraseIds());
					exam.setPhraseId(updatedId);
					break;
				case CLOZE:
					updatedId = generateUpdatedId(questionId, exam.getClozeIds());
					exam.setClozeId(updatedId);
					break;
				case TRANSLATE:
					updatedId = generateUpdatedId(questionId, exam.getTranslateIds());
					exam.setTranslateId(updatedId);
					break;
			}
			if (examMapper.updateQuestionId(exam) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(ExamError.QUESTION_DELETE);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject loadJoinExam(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		Integer pageNum = jParams.getInteger("pageNum");
		Integer joinStatus = jParams.getInteger("joinStatus");
		String token = jParams.getString("token");
		if (pageNum != null && joinStatus != null) {
			User user = globalContext.getUserToken().get(token);
			List<Integer> examIds = userPaperMapper.findExamIdByUserId(user.getUserId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", examIds);
			map.put("isActive", Consts.ACTIVE);
			PageHelper.startPage(pageNum, PageSize.JOIN_EXAM_RECORD);
			PageInfo<Exam> pageInfo = null;
			List<Exam> joinedExam = null;
			List<Exam> unjoinedExam = null;
			if (examIds != null && examIds.size() > 0) {
				if (joinStatus == Consts.EXAM_JOINED) {
					//joinedExam = examMapper.findJoinedExam(map);
					pageInfo = new PageInfo<Exam>(joinedExam);
					detail.put("joinedExams", joinedExam);
				} else {
					unjoinedExam = examMapper.findUnjoinedExam(map);
					pageInfo = new PageInfo<Exam>(unjoinedExam);
					detail.put("unjoinedExams", unjoinedExam);
				}
			} else {//用户未参加任何考试
				unjoinedExam = examMapper.findExamsBySatus(Consts.ACTIVE);
				pageInfo = new PageInfo<Exam>(unjoinedExam);
				detail.put("unjoinedExams", unjoinedExam);
			}
			result.setDetail(detail);
			result.setPageInfo(pageInfo);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject startExam(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer examId = jParams.getInteger("examId");
		String token = jParams.getString("token");
		if (examId != null) {
			Exam exam = examMapper.findExamById(examId);
			if (exam != null) {
				globalContext.getCurrentExam().put(token, exam);
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject loadStartExam(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		Exam exam = globalContext.getCurrentExam().get(token);
		if(exam != null){
			if(exam.getRadioIds() != null){
				List<Radio> radios = radioMapper.findRadiosByIds(exam.getRadioIds());
				detail.put("radios", radios);
			} else {
				detail.put("radios", null);
			}
			if(exam.getPhraseIds() != null){
				List<Phrase> phrases = phraseMapper.findPhrasesByIds(exam.getPhraseIds());
				detail.put("phrases", phrases);
			} else {
				detail.put("phrases", null);
			}
			if(exam.getBlankIds() != null){
				List<Blank> blanks = blankMapper.findBlanksByIds(exam.getBlankIds());
				detail.put("blanks", blanks);
			} else {
				detail.put("blanks", null);
			}
			if(exam.getTranslateIds() != null){
				List<Translate> translates = translateMapper.findTranslatesByIds(exam.getTranslateIds());
				detail.put("translates", translates);
			} else {
				detail.put("translates", null);
			}
			if(exam.getClozeIds() != null){
				List<Cloze> clozes = clozeMapper.findClozesByIds(exam.getClozeIds());
				detail.put("clozes", clozes);
			} else {
				detail.put("clozes", null);
			}
			detail.put("examId", exam.getExamId());
			detail.put("examName", exam.getName());
			detail.put("bak", exam.getBak());
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject saveUserExam(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String token = jParams.getString("token");
		Integer examId = jParams.getInteger("examId");
		String[] radioAnswers = jParams.getObject("radioAnswers", String[].class);
		String[] phraseAnswers = jParams.getObject("phraseAnswers", String[].class);
		String[] clozeAnswers = jParams.getObject("clozeAnswers", String[].class);
		String[] blankAnswers = jParams.getObject("blankAnswers", String[].class);
		String[] translateAnswers = jParams.getObject("translateAnswers", String[].class);
		if (examId != null && radioAnswers != null && phraseAnswers != null
				&& clozeAnswers != null && blankAnswers != null
				&& translateAnswers != null) {
			UserPaper userPaper = new UserPaper();
			userPaper.setAnswerDate(new Date());
			userPaper.setExamId(examId);
			userPaper.setBlanks(generateIds(blankAnswers));
			userPaper.setClozes(generateIds(clozeAnswers));
			userPaper.setPhrases(generateIds(phraseAnswers));
			userPaper.setTranslates(generateIds(translateAnswers));
			userPaper.setRadios(generateIds(radioAnswers));
			userPaper.setStatus(Consts.USER_PAPER_UNDO);
			userPaper.setUserId(globalContext.getUserToken().get(token).getUserId());
			if (userPaperMapper.saveUserPaper(userPaper) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(ExamError.SAVE_USER_EXAM_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject loadExamRecord(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		Integer pageNum = jParams.getInteger("pageNum");
		if (pageNum != null) {
			User user = globalContext.getUserToken().get(token);
			PageHelper.startPage(pageNum, PageSize.EXAM_RECORD_COUNT);
			List<UserPaper> userPapers = userPaperMapper.findExamsByUserId(user.getUserId());
			PageInfo<UserPaper> pageInfo = new PageInfo<>(userPapers);
			detail.put("examRecords", pageInfo.getList());
			result.setDetail(detail);
			result.setPageInfo(pageInfo);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	/**
	 * 生成更新后的题目id集合字符串
	 * 
	 * @param questionId
	 * @param ids
	 * @return
	 */
	private String generateUpdatedId(Integer questionId, List<Integer> ids){
		String result = "";
		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i) != questionId) {
				if (i != ids.size() - 1) {
					result += ids.get(i) + ";";
				} else {
					result += ids.get(i);
				}
			}
		}
		return result;
	}
	
	/**
	 * 形成ID字符串
	 * 
	 * @param ids
	 * @return
	 */
	private String generateIds(String[] ids) {
		if (ids != null) {
			String result = "";
			for (int i = 0; i < ids.length; i++) {
				if (i == ids.length - 1) {
					if ("".equals(ids[i])) {
						result += Consts.ANSWER_NULL;
					} else {
						result += ids[i];
					}
				} else {
					if ("".equals(ids[i])) {
						result += Consts.ANSWER_NULL + ";";
					} else {
						result += ids[i] + ";";
					}
				}
			}
			return result;
		}
		return null;
	}
	
	
	/**
	 * 形成ID字符串
	 * 
	 * @param ids
	 * @return
	 */
	private String generateIds(List<Integer> ids) {
		if (ids != null) {
			String result = "";
			for (int i = 0; i < ids.size(); i++) {
				if (i == ids.size() - 1) {
					result += ids.get(i);
				} else {
					result += ids.get(i) + ";";
				}
			}
			return result;
		}
		return null;
	}

	@Override
	public MJSONObject autoValidateName(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String name = jParams.getString("name");
		if (!Utils.isNullOrBlank(name)) {
			if (examMapper.findExamByName(name) == null) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(ExamError.AUTO_EXAM_NAME_EXISTED);
			}
		} else {
			result.setErrorCode(ExamError.AUTO_EXAM_NAME_IS_NULL);
		}
		return result;
	}

}
