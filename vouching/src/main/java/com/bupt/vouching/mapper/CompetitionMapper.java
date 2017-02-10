package com.bupt.vouching.mapper;

import java.util.List;

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

	/**
	 * 通过用户ID查询竞技记录
	 * 
	 * @param userId
	 * @return
	 */
	public List<Competition> findCompetitionsByUserId(Integer userId);

}
