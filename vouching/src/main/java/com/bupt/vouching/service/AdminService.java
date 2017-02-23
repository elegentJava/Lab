package com.bupt.vouching.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.MJSONObject;

/**
 * 管理员业务接口
 * 
 * @author Hogan
 * 
 */
public interface AdminService {

	/**
	 * 装载用户列表数据
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadUserList(JSONObject jParams);

	/**
	 * 重置密码
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject resetPassword(JSONObject jParams);

	/**
	 * 删除单个用户
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject deleteSinglerUser(JSONObject jParams);

	/**
	 * 装载添加用户页面的班级列表
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadClasses(JSONObject jParams);

	/**
	 * 添加用户
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject addUser(JSONObject jParams);

	/**
	 * 批量添加用户
	 * 
	 * @param params
	 * @param request
	 * @return
	 */
	public MJSONObject batchInsertUsers(Map<String, Object> params, HttpServletRequest request);

	/**
	 * 装载班级信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadClassList(JSONObject jParams);

	/**
	 * 删除一个班级
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject deleteSingleClass(JSONObject jParams);

	/**
	 * 更新班级状态
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject updateClassStatus(JSONObject jParams);

	/**
	 * 批量删除班级
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject batchDeleteClass(JSONObject jParams);

	/**
	 * 添加班级信息
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject addClass(JSONObject jParams);

	/**
	 * 批量删除用户
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject batchDeleteUsers(JSONObject jParams);

	/**
	 * 装载资源列表
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadResourceList(JSONObject jParams);

	/**
	 * 处理单个资源文件
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject dealSingleResource(JSONObject jParams);

	/**
	 * 装载班级指派的教师班级列表
	 * 
	 * @param jParams
	 * @return
	 */
	public MJSONObject loadTeacherClasses(JSONObject jParams);

}
