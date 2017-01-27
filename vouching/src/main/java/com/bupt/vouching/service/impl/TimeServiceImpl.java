package com.bupt.vouching.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
					if (!competitionMap.isEmpty()) {
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

	@Override
	public void competitionCreditListener() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Map<String, CompetitionSer> competitionMap = globalContext.getCompetitionMap();
				for(Entry<String, CompetitionSer> e : competitionMap.entrySet()){
					CompetitionSer competitionSer = e.getValue();
					List<User> users = competitionSer.getUsers();
					if (users.size() == Consts.MATECHED_COUNT) {
						boolean flag = true;
						for (User user : competitionSer.getUsers()) {
							if (user.getCompetitionScore() == null) {
								flag = false;
								break;
							}
						}
						if (flag) {
							Map<Integer, Integer> sortedUserMap = sortForCompetition(users);
							for (Entry<Integer, Integer> e1 : sortedUserMap.entrySet()) {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("userId", e1.getKey());
								map.put("credit", e1.getValue());
								userMapper.updateCredit(map);
							}
						}
					}
				}
			}
		}).start();
	}
	
	/**
	 * 为修改积分排序
	 * @param users
	 * @return
	 */
	private Map<Integer, Integer> sortForCompetition(List<User> users){
		Map<Integer, Integer> result = new HashMap<Integer,Integer>();
		
		return result;
	}

}
