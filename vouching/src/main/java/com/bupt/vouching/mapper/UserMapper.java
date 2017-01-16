package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

import com.bupt.vouching.bean.User;

/**
 * 用户数据层接口(mybatis自动实现)
 * 
 * @author Hogan
 * 
 */
public interface UserMapper {

	/**
	 * 通过账号密码获取用户信息
	 * 
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 * @return
	 */
	public User findUserByAP(String account, String password);

	/**
	 * 修改用户的在线状态
	 * 
	 * @param userId
	 *            用户ID
	 * @param isOline
	 *            在线状态
	 */
	public int updateUserOnlineStatus(Integer userId, Integer isOline);

	/**
	 * 修改用户的在线状态和登录时间
	 * 
	 * @param user
	 * @return
	 */
	public Integer updateUserStatusAndLastLoginDate(User user);

	/**
	 * 通过id和密码查找指定用户信息
	 * 
	 * @param map
	 * @return
	 */
	public User findUserByIdAndPassword(Map<String, Object> map);

	/**
	 * 修改用户密码
	 * 
	 * @param map
	 * @return
	 */
	public Integer updateUserPassword(Map<String, Object> map);

	/**
	 * 根据积分查询用户信息
	 * 
	 * @param maxRecord
	 * @return
	 */
	public List<User> findUserByCredit(int maxRecord);

	/**
	 * 查询用户和班级信息
	 * 
	 * @return
	 */
	public List<User> findUserAndClassByRole(int role);

	/**
	 * 只查询用户信息
	 * 
	 * @return
	 */
	public List<User> findSingleUsersByRole(int role);

	/**
	 * 修改密码
	 * 
	 * @param map
	 * @return
	 */
	public Integer updatePassword(Map<String, Object> map);

	/**
	 * 根据id删除单个用户
	 * 
	 * @param userId
	 * @return
	 */
	public Integer deleteSingleUserById(Integer userId);

	/**
	 * 保存用户
	 * 
	 * @param user
	 * @return
	 */
	public Integer saveUser(User user);

	/**
	 * 根据班级查找用户信息
	 * 
	 * @param classId
	 * @return
	 */
	public List<User> findUsersByClassId(Integer classId);

	/**
	 * 通过id查找用户
	 * 
	 * @param userId
	 * @return
	 */
	public User findUserById(int userId);

	/**
	 * 通过账号查找用户信息
	 * 
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);
	
	/**
	 * 批量增加用户数据
	 * 
	 * @param users
	 */
	public Integer batchInsert(List<User> users);

	/**
	 * 批量删除用户
	 * 
	 * @param userIds
	 * @return
	 */
	public Integer batchDelete(Integer[] userIds);
}
