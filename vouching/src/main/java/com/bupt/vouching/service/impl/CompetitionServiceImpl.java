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
			for (User e : competition.getUsers()) {
				if (e.getUserId().equals(globalContext.getUserToken().get(token).getUserId())) {
					detail.put("users", competition.getUsers());
					result.setDetail(detail);
					result.setErrorCode(ErrorCode.SUCCESS);
					return result;
				}
			}
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
		String[] answer = new String[5];
		if (answers != null) {
			List<? extends Question> questions = globalContext
					.getCompetitionMap()
					.get(globalContext.getMatchingMap().get(token))
					.getQuestions();
			for (Question question : questions) {
				if (answers[count] != null) {
					if (question instanceof Radio) {
						if (OptionType.byId(Integer.parseInt(answers[count])).getDescription().equals(question.getAnswer())) {
							score++;
						}
				}
				answer[count] = question.getAnswer();
				count++;
			}
			Competition newCompetition = new Competition();
			newCompetition.setDate(new Date());
			newCompetition.setScore(score);
			newCompetition.setUserId(user.getUserId());
			if (competitionMapper.addCompetition(newCompetition) == Consts.DATA_SINGLE_SUCCESS) {
				user.setCompetitionScore(score);
				detail.put("answers", answer);
				detail.put("score", score);
				result.setDetail(detail);
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(PracticeError.SHOW_ANSWER_FAILD);
			}
		} }else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

}
