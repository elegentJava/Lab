package com.bupt.vouching.service.impl;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bupt.vouching.bean.User;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.mapper.UserMapper;
import com.bupt.vouching.service.TimeService;
import com.bupt.vouching.service.bean.Competition;

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

	/**
	 * session失效检查
	 */
	public void sessionTimer() {
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
					List<Competition> competitionQueue = globalContext.getCompetitionQueue();
					LinkedList<String> watchingQueue = globalContext.getWatchingQueue();
					if (competitionQueue.size() != 0) {
						Competition competition = competitionQueue.get(competitionQueue.size() - 1);
						int tempSize = Consts.MATECHED_COUNT - competition.getUsers().size();
						if (tempSize != 0) {//队列最后一个元素还没有满
							while(tempSize-- != 0){
								while(!watchingQueue.isEmpty()){
									User user = globalContext.getUserToken().get(watchingQueue.poll());
									competition.getUsers().add(user);
								}
							}
							//随机生成一套试题
							
						} else {//队列最后一个元素已经满了
							Competition newCompetition = new Competition();
							for (int i = 0; i < Consts.MATECHED_COUNT; i++) {
								while(tempSize-- != 0){
									while(!watchingQueue.isEmpty()){
										User user = globalContext.getUserToken().get(watchingQueue.poll());
										competition.getUsers().add(user);
									}
								}
							}
							//随机生成一套试题
							
							competitionQueue.add(newCompetition);
						}
					} else {//系统初始化的操作
						competitionQueue.add(new Competition());
					}
				}
			}
		}).start();
	}

}
