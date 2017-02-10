package com.bupt.vouching.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bupt.vouching.bean.Question;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.mapper.CompetitionMapper;
import com.bupt.vouching.mapper.RadioMapper;
import com.bupt.vouching.mapper.UserMapper;
import com.bupt.vouching.service.TimeService;
import com.bupt.vouching.service.bean.CompetitionSer;
import com.bupt.vouching.type.Credit;

/**
 * 定时任务
 * 
 * @author Hogan
 * 
 */
@Service("timeService")
public class TimeServiceImpl implements TimeService {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private GlobalContext globalContext;

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RadioMapper radioMapper;
	
	@Resource
	private CompetitionMapper competitionMapper;
	
	/**
	 * session失效检查
	 */
	@Override
	public void sessionInvalidListener() {
		log.info("校验用户session信息");
		Map<String, User> userSessionMap = globalContext.getUserToken();
		if (userSessionMap != null) {
			for (Entry<String, User> e : userSessionMap.entrySet()) {
				Calendar calendar = Calendar.getInstance();
				Calendar lastOperateCalendar = Calendar.getInstance();
				lastOperateCalendar.setTime(e.getValue().getLogicTime());
				calendar.add(Calendar.MINUTE, -30);
				if (calendar.after(lastOperateCalendar)) {
					Integer userId = e.getValue().getUserId();
					if (userMapper.updateUserOnlineStatus(userId, Consts.USER_NOT_ONLINE) == Consts.DATA_SINGLE_SUCCESS) {
						userSessionMap.remove(e.getKey());
						log.info("用户:" + userId + "-----session失效!");
					}
				}
			}
		}
	}

	/**
	 * 检测匹配队列
	 */
	public void competitionQueueListener() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (;;) {
					Map<String, CompetitionSer> competitionMap = globalContext.getCompetitionMap();
					LinkedList<String> watchingQueue = globalContext.getWatchingQueue();
					if (!competitionMap.isEmpty()) {//非初始化操作
						CompetitionSer competition = null;
						for (Entry<String, CompetitionSer> e : competitionMap.entrySet()) {
							if (e.getValue().getUsers().size() < Consts.MATECHED_COUNT) {
								competition = e.getValue();
								break;
							}
						}
						if (competition != null) {// 队列最后一个元素还没有满
							int tempSize = Consts.MATECHED_COUNT - competition.getUsers().size();
							while (tempSize-- != 0) {
								String userToken = watchingQueue.poll();
								if (userToken != null) {
									globalContext.getMatchingMap().put(userToken, competition.getId());
									User user = globalContext.getUserToken().get(userToken);
									competition.getUsers().add(user);
								} else {
									break;
								}
							}
							// 随机生成一套试题(单选题)
							if(competition.getQuestions() == null){
								randomGenerateQuestion(competition);
							}
						} else {// 队列最后一个元素已经满了
							CompetitionSer newCompetition = new CompetitionSer();
							for (int i = 0; i < Consts.MATECHED_COUNT; i++) {
								String userToken = watchingQueue.poll();
								if (userToken != null) {
									globalContext.getMatchingMap().put(userToken, newCompetition.getId());
									User user = globalContext.getUserToken().get(userToken);
									newCompetition.getUsers().add(user);
								} else {
									break;
								}
							}
							competitionMap.put(newCompetition.getId(),newCompetition);
							// 随机生成一套试题(单选题)
							if(newCompetition.getQuestions() == null){
								randomGenerateQuestion(newCompetition);
							}
						}
					} else {// 系统初始化的操作
						CompetitionSer newCompetition = new CompetitionSer();
						competitionMap.put(newCompetition.getId(),newCompetition);
					}
				}
			}
		},"competitionQueueListener").start();
	}
	
	@Override
	public void competitionCreditListener() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(;;){
					Map<String, CompetitionSer> competitionMap = globalContext.getCompetitionMap();
					if (!competitionMap.isEmpty()) {
						for (Entry<String, CompetitionSer> e : competitionMap.entrySet()) {
							CompetitionSer competitionSer = e.getValue();
							if (!competitionSer.getScoreHandle()) {
								boolean flag = true;
								synchronized (this) {
									List<User> users = competitionSer.getUsers();
									if (users.size() > 0) {
										for (User user : users) {
											if (user.getCompetitionScore() == null) {
												flag = false;
												break;
											}
										}
									} else {
										flag = false;
									}
									if (flag) {//所有用户都已经录入分数
										sortForCredit(users);
										for (User user : users) {
											Map<String, Object> map = new HashMap<String, Object>();
											map.put("userId", user.getUserId());
											map.put("credit", user.getCredit());
											userMapper.updateCredit(map);
										}
										competitionSer.setScoreHandle(true);
									}
								}
							}
						}
					}
				}
			}
		},"competitionCreditListener").start();
	}
	
	@Override
	public void removeCompetitionInfoListener() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(;;){
					Map<String, CompetitionSer> competitionMap = globalContext.getCompetitionMap();
					Set<String> competitionIds = competitionMap.keySet();
					for(String e : competitionIds){
						if (!globalContext.getMatchingMap().containsValue(e)) {
							competitionMap.remove(e);
						}
					}
				}
			}
		}).start();
	}
	
	/**
	 * 随机生成一套试题
	 * @param competition
	 */
	private void randomGenerateQuestion(CompetitionSer competition){
		Map<String, Integer> map = new HashMap<String, Integer>();
		/*map.put("chapterId", (int) (Math.random()*5));
		map.put("level", (int) (Math.random()*3));*/
		map.put("chapterId", 1);
		map.put("level", 0);
		map.put("random", Consts.QUESTION_COUNT);
		List<? extends Question> questions = radioMapper.findRadiosByRandom(map);	
		competition.setQuestions(questions);
	}
	
	/**
	 * 为积分计算排序
	 * 
	 * @param users
	 * @return
	 */
	private void sortForCredit(List<User> users){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<Integer[]> result = new ArrayList<Integer[]>();
		Integer[] scores = new Integer[Consts.MATECHED_COUNT];
		for (int i = 0 ; i < users.size() ; i++) {
			scores[i] = users.get(i).getCompetitionScore();
		}
		Arrays.sort(scores);
		for (int i = scores.length - 1; i >= 0; i--) {
			if (i != scores.length - 1) {
				int rank = result.get(scores.length - i - 2)[1];
				if (scores[i] == scores[i + 1]) {
					result.add(new Integer[] { scores[i], rank });
				} else {
					result.add(new Integer[] { scores[i], rank + 1 });
				}
			} else {
				result.add(new Integer[] { scores[i], 1 });
			}
		}
		for(Integer[] e : result){
			map.put(e[0], e[1]);
		}
		for (User user : users) {
			int score = map.get(user.getCompetitionScore());
			user.setTempCredit(Credit.byRank(score).getCredit());
			user.setCredit(Credit.byRank(score).getCredit() + user.getCredit());
		}
	}

}
