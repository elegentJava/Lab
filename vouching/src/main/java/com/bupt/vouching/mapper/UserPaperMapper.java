package com.bupt.vouching.mapper;

import java.util.List;

import com.bupt.vouching.bean.UserPaper;

/**
 * 用户试卷数据层接口
 * 
 * @author Hogan
 * 
 */
public interface UserPaperMapper {

	/**
	 * 通过用户ID查询试卷ID
	 * 
	 * @param userId
	 * @return
	 */
	public List<Integer> findExamIdByUserId(Integer userId);

	/**
	 * 保存用户试卷
	 * 
	 * @param userPaper
	 * @return
	 */
	public Integer saveUserPaper(UserPaper userPaper);

	/**
	 * 通过用户ID查询试卷
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserPaper> findExamsByUserId(Integer userId);
}
