package com.bupt.vouching.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.bean.Competition;
import com.bupt.vouching.bean.Question;
import com.bupt.vouching.bean.Radio;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.mapper.CompetitionMapper;
import com.bupt.vouching.mapper.UserMapper;
import com.bupt.vouching.service.CompetitionService;
import com.bupt.vouching.service.bean.CompetitionSer;
import com.bupt.vouching.type.OptionType;
import com.bupt.vouching.type.PageSize;
import com.bupt.vouching.type.error.CompetitionError;
import com.bupt.vouching.type.error.ErrorCode;
import com.bupt.vouching.type.error.PracticeError;
import com.bupt.vouching.util.page.PageHelper;
import com.bupt.vouching.util.page.PageInfo;

/**
 * 竞技业务实现类
 * 
 * @author Hogan
 *
 */
@Service("competitionService")
public class CompetitionServiceImpl implements CompetitionService {
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private CompetitionMapper competitionMapper;
	
	@Resource
	private GlobalContext globalContext;
	
	@Override
	public MJSONObject loadRank(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<User> users = userMapper.findUserByCredit(PageSize.COMPETITION_RANK_RESULT.getPageSize());
		detail.put("ranks", users);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject matching(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		Map<String, String> matchingMap = globalContext.getMatchingMap();
		if (matchingMap.containsKey(token)) {
			CompetitionSer competition = globalContext.getCompetitionMap().get(matchingMap.get(token));
			detail.put("users", competition.getUsers());
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
			return result;
		} else {
			result.setErrorCode(CompetitionError.MATCH_NOT_COMPLETE);
		}
		return result;
	}

	@Override
	public MJSONObject joinCompetition(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String token = jParams.getString("token");
		globalContext.getWatchingQueue().add(token);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject loadCompetitionExam(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		String competitionId = globalContext.getMatchingMap().get(token);
		List<? extends Question> questions = globalContext.getCompetitionMap().get(competitionId).getQuestions();
		detail.put("questions", questions);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject showAnswer(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token =jParams.getString("token");
		User user = globalContext.getUserToken().get(token);
		String[] answers = jParams.getObject("answers", String[].class);
		Integer score = 0;
		Integer count = 0;
		if (answers != null) {
			String competitionId = globalContext.getMatchingMap().get(token);
			if (competitionId != null) {
				CompetitionSer competitionSer = globalContext.getCompetitionMap().get(competitionId);
				if (competitionSer != null) {
					List<? extends Question> questions = competitionSer.getQuestions();
					for (Question question : questions) {
						if (answers[count] != null) {
							if (question instanceof Radio) {//暂时只有选择题
								if (OptionType.byId(Integer.parseInt(answers[count])).getDescription().equals(question.getAnswer())) {
									score++;
								}
							}
							count++;
						}
					}
					//暂存用户的竞技得分，通过独立线程进行处理
					List<User> users = competitionSer.getUsers();
					for (User e : users) {
						if (e.getUserId() == user.getUserId()) {
							e.setCompetitionScore(score);
							break;
						}
					}
					Competition newCompetition = new Competition();
					newCompetition.setDate(new Date());
					newCompetition.setScore(score);
					newCompetition.setUserId(user.getUserId());
					if (competitionMapper.addCompetition(newCompetition) == Consts.DATA_SINGLE_SUCCESS) {
						user.setCompetitionScore(score);
						detail.put("answers", competitionSer.getAnswers());
						detail.put("score", score);
						result.setDetail(detail);
						result.setErrorCode(ErrorCode.SUCCESS);
					} else {
						result.setErrorCode(PracticeError.SHOW_ANSWER_FAILD);
					}
				}
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject loadCompetitionRecord(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		Integer pageNum = jParams.getInteger("pageNum");
		User user = globalContext.getUserToken().get(token);
		PageHelper.startPage(pageNum, PageSize.COMPETITION_RECORD);
		List<Competition> competitions = competitionMapper.findCompetitionsByUserId(user.getUserId());
		PageInfo<Competition> pageInfo = new PageInfo<Competition>(competitions);
		detail.put("records", pageInfo.getList());
		result.setDetail(detail);
		result.setPageInfo(pageInfo);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject creditHandle(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		User user = globalContext.getUserToken().get(token);
		String competitionId = globalContext.getMatchingMap().get(token);
		if (competitionId != null) {
			CompetitionSer competitionSer = globalContext.getCompetitionMap().get(competitionId);
			if (competitionSer != null) {
				if (competitionSer.getScoreHandle()) {
					for (User e : competitionSer.getUsers()) {
						if (user.getUserId() == e.getUserId()) {
							detail.put("credit", e.getTempCredit());
							result.setDetail(detail);
							result.setErrorCode(ErrorCode.SUCCESS);
							return result;
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public MJSONObject removeCompetitionInfo(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String token = jParams.getString("token");
		String competitionId = globalContext.getMatchingMap().get(token);
		if (competitionId != null) {
			globalContext.getMatchingMap().remove(token);
			result.setErrorCode(ErrorCode.SUCCESS);
		}
		return result;
	}

}
