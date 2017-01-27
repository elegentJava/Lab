package com.bupt.vouching.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.bean.Chapter;
import com.bupt.vouching.bean.Practice;
import com.bupt.vouching.bean.Question;
import com.bupt.vouching.bean.Radio;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.mapper.BlankMapper;
import com.bupt.vouching.mapper.ChapterMapper;
import com.bupt.vouching.mapper.ClozeMapper;
import com.bupt.vouching.mapper.PhraseMapper;
import com.bupt.vouching.mapper.PracticeMapper;
import com.bupt.vouching.mapper.RadioMapper;
import com.bupt.vouching.mapper.TranslateMapper;
import com.bupt.vouching.service.PracticeService;
import com.bupt.vouching.service.bean.QuestionLevel;
import com.bupt.vouching.service.bean.QuestionType;
import com.bupt.vouching.type.LevelType;
import com.bupt.vouching.type.OptionType;
import com.bupt.vouching.type.PageSize;
import com.bupt.vouching.type.QuestionCategory;
import com.bupt.vouching.type.error.ErrorCode;
import com.bupt.vouching.type.error.ExamError;
import com.bupt.vouching.type.error.PracticeError;
import com.bupt.vouching.util.page.PageHelper;
import com.bupt.vouching.util.page.PageInfo;

/**
 * 练习平台业务实现
 * 
 * @author Hogan
 *
 */
@Service("practiceService")
public class PracticeServiceImpl implements PracticeService {
	
	@Resource
	private ChapterMapper chapterMapper;
	
	@Resource
	private GlobalContext globalContext;
	
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
	private PracticeMapper practiceMapper;

	@Override
	public MJSONObject loadTestSelect(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<QuestionType> categorys = new ArrayList<QuestionType>();
		List<QuestionLevel> levels = new ArrayList<QuestionLevel>();
		List<Chapter> chapters = chapterMapper.findAllChaptersByActiveStatus(Consts.ACTIVE);
		for (QuestionCategory qc : QuestionCategory.values()) {
			categorys.add(new QuestionType(qc.getId(), qc.getTag(), qc.getName()));
		}
		for (LevelType e : LevelType.values()) {
			levels.add(new QuestionLevel(e.getId(), e.getDescription()));
		}
		detail.put("categorys", categorys);
		detail.put("levels", levels);
		detail.put("chapters", chapters);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject generateTest(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String token = jParams.getString("token");
		Integer chapterId = jParams.getInteger("chapterId");
		Integer categoryId = jParams.getInteger("categoryId");
		Integer levelId = jParams.getInteger("levelId");
		Integer count = jParams.getInteger("count");
		if(chapterId != null && categoryId != null && levelId != null && count != null){
			List<? extends Question> questions = null;
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("level", levelId);
			map.put("random", count);
			map.put("chapterId", chapterId);
			switch (QuestionCategory.byId(categoryId)) {
				case BLANK:
					questions = blankMapper.findBlanksByRandom(map);
					break;
				case RADIO:
					questions = radioMapper.findRadiosByRandom(map);
					break;
				case CLOZE:
					questions = clozeMapper.finClozesByRandom(map);
					break;
				case PHRASE:
					questions = phraseMapper.findPhrasesByRandom(map);
					break;
				case TRANSLATE:
					questions = translateMapper.findTranslatesByRandom(map);
					break;
				default:
					result.setErrorCode(ExamError.QUESTION_CATEGORY_INVALID);
					return result;
			}
			Map<String, List<? extends Question>> currentPractice = globalContext.getCurrentPractice();
			currentPractice.put(token, questions);
			result.setErrorCode(ErrorCode.SUCCESS);
		}
		return result;
	}

	@Override
	public MJSONObject loadStartTest(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		Map<String, List<? extends Question>> currentPractice = globalContext.getCurrentPractice();
		List<? extends Question> questions = currentPractice.get(token);
		if (questions != null) {
			detail.put("questions", questions);
			detail.put("chapterId", questions.get(0).getChapterId());
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(PracticeError.LOAD_START_FAILD);
		}
		return result;
	}

	@Override
	public MJSONObject loadTestRecord(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		Integer pageNum = jParams.getInteger("pageNum");
		User user = globalContext.getUserToken().get(token);
		PageHelper.startPage(pageNum, PageSize.TEST_RECORD);
		List<Practice> practices = practiceMapper.findPracticesByUserId(user.getUserId());
		PageInfo<Practice> pageInfo = new PageInfo<Practice>(practices);
		detail.put("records", pageInfo.getList());
		result.setDetail(detail);
		result.setPageInfo(pageInfo);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject showAnswerAndScore(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token =jParams.getString("token");
		String[] answers = jParams.getObject("answers", String[].class);
		Integer chapterId = jParams.getInteger("chapterId");
		Integer score = 0;
		Integer count = 0;
		String[] answer = new String[5];
		if (answers != null) {
			List<? extends Question> questions = globalContext.getCurrentPractice().get(token);
			for (Question question : questions) {
				if (answers[count] != null) {
					if (question instanceof Radio) {
						if (OptionType.byId(Integer.parseInt(answers[count])).getDescription().equals(question.getAnswer())) {
							score++;
						}
					} else {
						if (answers[count].equals(question.getAnswer())) {
							score++;
						}
					}
				}
				answer[count] = question.getAnswer();
				count++;
			}
			Practice practice = new Practice();
			practice.setChapterId(chapterId);
			practice.setDate(new Date());
			practice.setScore(score);
			practice.setUserId(globalContext.getUserToken().get(token).getUserId());
			if (practiceMapper.addPractice(practice) == Consts.DATA_SINGLE_SUCCESS) {
				detail.put("answers", answer);
				detail.put("score", score);
				result.setDetail(detail);
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(PracticeError.SHOW_ANSWER_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
}
