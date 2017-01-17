package com.bupt.vouching.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.mapper.UserMapper;
import com.bupt.vouching.service.CompetitionService;
import com.bupt.vouching.service.bean.Competition;
import com.bupt.vouching.type.PageSize;
import com.bupt.vouching.type.error.CompetitionError;
import com.bupt.vouching.type.error.ErrorCode;

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
		List<Competition> competitionQueue = globalContext.getCompetitionQueue();
		if(competitionQueue != null){
			for(Competition competition : competitionQueue){
				for (User e : competition.getUsers()) {
					if (e.getUserId().equals(globalContext.getUserToken().get(token).getUserId())) {
						detail.put("users", competition.getUsers());
						result.setDetail(detail);
						result.setErrorCode(ErrorCode.SUCCESS);
						return result;
					}
				}
			}
		}
		result.setErrorCode(CompetitionError.MATCH_NOT_COMPLETE);
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

}
