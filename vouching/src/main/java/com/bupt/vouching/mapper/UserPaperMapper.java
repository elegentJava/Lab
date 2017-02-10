package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

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
	 * 查询用户已经参加过的考试信息
	 * 
	 * @param map
	 * @return
	 */
	public List<UserPaper> findJoinedExam(Map<String, Object> map);

	/**
	 * 通过用户ID和试卷处理状态查询试卷信息
	 * 
	 * @param map
	 * @return
	 */
	public List<UserPaper> findUserpaperByUserIdAndStatus(Map<String, Object> map);
}
