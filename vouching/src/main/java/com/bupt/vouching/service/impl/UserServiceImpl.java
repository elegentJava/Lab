package com.bupt.vouching.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.mapper.UserMapper;
import com.bupt.vouching.service.UserService;
import com.bupt.vouching.type.error.ErrorCode;
import com.bupt.vouching.type.error.UserError;
import com.bupt.vouching.util.Utils;

/**
 * 用户业务层实现
 * 
 * @author Hogan
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private GlobalContext globalContext;

	@Override
	public MJSONObject loginValidate(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String account = jParams.getString("account");
		String password = jParams.getString("password");
		Boolean isFrontLogin = jParams.getBoolean("flag");
		if (isFrontLogin != null) {
			if (!Utils.isNullOrBlank(account)) {
				if (!Utils.isNullOrBlank(password)) {
					User user = userMapper.findUserByAP(account, password);
					if (user != null) {
						if (isFrontLogin && user.getRole() != Consts.ROLE_ADMIN
								|| !isFrontLogin && user.getRole() == Consts.ROLE_ADMIN) {
							Map<String, User> userToken = globalContext.getUserToken();
							user.setLoginDate(new Date());
							userMapper.updateUserOnlineStatus(user.getUserId(), Consts.USER_IS_ONLINE);
							String token = Utils.UUID();
							userToken.put(token, user);
							globalContext.setUserToken(userToken);
							detail.put("token", token);
							result.setDetail(detail);
							result.setErrorCode(ErrorCode.SUCCESS);
						} else {
							result.setErrorCode(UserError.NO_PRIVILEGE);
						}
					} else {
						result.setErrorCode(UserError.AP_NOT_MATCH);
					}
				} else {
					result.setErrorCode(UserError.PASSWORD_NULL);
				}
			} else {
				result.setErrorCode(UserError.ACCOUNT_IS_NULL);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}
	
	@Override
	public MJSONObject logout(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String token = jParams.getString("token");
		User user = globalContext.getUserToken().get(token);
		user.setIsOnline(Consts.USER_NOT_ONLINE);
		user.setLastLoginDate(user.getLoginDate());
		globalContext.getUserToken().remove(token);
		if (userMapper.updateUserStatusAndLastLoginDate(user) == Consts.DATA_SINGLE_SUCCESS) {
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(UserError.LOGOUT_FAILD);
		}
		return result;
	}
	
	@Override
	public MJSONObject modifyPW(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String token = jParams.getString("token");
		String oldPassword = jParams.getString("oldPassword");
		String newPassword = jParams.getString("newPassword");
		String confirmPassword = jParams.getString("confirmPassword");
		if (!Utils.isNullOrBlank(oldPassword, newPassword, confirmPassword)) {
			if (newPassword.equals(confirmPassword)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", globalContext.getUserToken().get(token).getUserId());
				map.put("password", oldPassword);
				if (userMapper.findUserByIdAndPassword(map) != null) {
					map.put("password", newPassword);
					if (userMapper.updateUserPassword(map) == Consts.DATA_SINGLE_SUCCESS) {
						result.setErrorCode(ErrorCode.SUCCESS);
					} else {
						result.setErrorCode(UserError.MODIFY_PASSWORD_FAILD);
					}
				} else {
					result.setErrorCode(UserError.OLD_PASSWORD_INCORRECT);
				}
			} else {
				result.setErrorCode(UserError.TWICE_PASSWORD_NOT_MATCH);
			}
		} else {
			result.setErrorCode(UserError.PASSWORD_NULL);
		}
		return result;
	}

}
