package com.bupt.vouching.service;

/**
 * 定时任务接口
 * 
 * @author Hogan
 * 
 */
public interface TimeService {

	/**
	 * session失效监听器
	 */
	public void sessionInvalidListener();

	/**
	 * 匹配队列监听器
	 */
	public void competitionQueueListener();
	
	/**
	 * 竞技积分监听器
	 */
	public void competitionCreditListener();
}
