package com.bupt.vouching.mapper;

import com.bupt.vouching.bean.Competition;

/**
 * 竞技数据层接口
 * 
 * @author Hogan
 * 
 */
public interface CompetitionMapper {

	/**
	 * 添加一个竞技实体
	 * 
	 * @param competition
	 * @return
	 */
	public Integer addCompetition(Competition competition);

}
