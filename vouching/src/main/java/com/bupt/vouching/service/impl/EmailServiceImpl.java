package com.bupt.vouching.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.bean.Class;
import com.bupt.vouching.bean.Email;
import com.bupt.vouching.bean.User;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.mapper.ClassMapper;
import com.bupt.vouching.mapper.EmailMapper;
import com.bupt.vouching.mapper.UserMapper;
import com.bupt.vouching.service.EmailService;
import com.bupt.vouching.type.PageSize;
import com.bupt.vouching.type.error.EmailError;
import com.bupt.vouching.type.error.ErrorCode;
import com.bupt.vouching.util.Utils;
import com.bupt.vouching.util.page.PageHelper;
import com.bupt.vouching.util.page.PageInfo;

/**
 * 邮件业务层实现
 * 
 * @author Hogan
 * 
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Resource
	private EmailMapper emailMapper;

	@Resource
	private ClassMapper classMapper;
	
	@Resource
	private UserMapper userMapper;

	@Resource
	private GlobalContext globalContext;

	@Override
	public MJSONObject loadEmailBox(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		Integer emailType = jParams.getInteger("emailType");
		Integer pageNum = jParams.getInteger("pageNum");
		if (emailType != null && pageNum != null) {
			User user = globalContext.getUserToken().get(token);
			PageHelper.startPage(pageNum, PageSize.EMAIL_BOX_RECORD);
			List<Email> emails = null;
			PageInfo<Email> pageInfo = null;
			if (Consts.EMAIL_TYPE_RECEIVE == emailType) {
				emails = emailMapper.findReceiveEmails(user.getUserId());
				pageInfo = new PageInfo<Email>(emails);
				detail.put("receiveEmails", emails);
			} else {
				emails = emailMapper.findSendEmails(user.getUserId());
				pageInfo = new PageInfo<Email>(emails);
				detail.put("sendEmails", emails);
			}
			result.setDetail(detail);
			result.setPageInfo(pageInfo);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject loadUsersInClass(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		Integer classId = jParams.getInteger("classId");
		String token = jParams.getString("token");
		if (classId != null) {
			List<User> users = userMapper.findUsersByClassId(classId);
			Iterator<User> iterator = users.iterator();
			while(iterator.hasNext()){
				if (iterator.next().getUserId() == globalContext.getUserToken().get(token).getUserId()) {
					iterator.remove();
					continue;
				}
			}
			detail.put("users", users);
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject sendEmail(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String token = jParams.getString("token");
		String subject = jParams.getString("subject");
		String content = jParams.getString("content");
		String receiverIds = jParams.getString("receivers");
		if (!Utils.isNullOrBlank(subject)) {
			if (!Utils.isNullOrBlank(content)) {
				if (!Utils.isNullOrBlank(receiverIds)) {
					String[] receivers = receiverIds.split(";");
					for (int i = 0; i < receivers.length; i++) {
						User receiver = userMapper.findUserById(Integer.parseInt(receivers[i]));
						User sender = globalContext.getUserToken().get(token);
						if (receiver != null && receiver.getUserId() != sender.getUserId()) {
							Email email = new Email();
							email.setContent(content);
							email.setDate(new Date());
							email.setIsRead(Consts.EMAIL_UNREAD);
							email.setReceiveDel(Consts.EMAIL_NOT_DELETE);
							email.setReceiveName(receiver.getName());
							email.setReceiveId(receiver.getUserId());
							email.setSendDel(Consts.EMAIL_NOT_DELETE);
							email.setSendId(sender.getUserId());
							email.setSendName(sender.getName());
							email.setSubject(subject);
							if (emailMapper.saveEmail(email) != Consts.DATA_SINGLE_SUCCESS) {
								result.setErrorCode(EmailError.SEND_EMAIL_FAILD);
								return result;
							}
						} else {
							result.setErrorCode(EmailError.RECEIVER_EMAIL_INVALID);
							return result;
						}
					}
					result.setErrorCode(ErrorCode.SUCCESS);
				} else {
					result.setErrorCode(EmailError.RECEIVER_IS_NULL);
				}
			} else {
				result.setErrorCode(EmailError.CONTENT_IS_NULL);
			}
		} else {
			result.setErrorCode(EmailError.SUBJECT_IS_NULL);
		}
		return result;
	}

	@Override
	public MJSONObject loadClassList(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<Class> classes = classMapper.findClassByStatus(Consts.ACTIVE);
		detail.put("classes", classes);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject loadEmailDetail(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		Integer[] emailDetail = globalContext.getEmailDetail().get(token);
		if (emailDetail[1] == Consts.EMAIL_TYPE_RECEIVE) {// 收件箱查看修改状态
			Email email = new Email();
			email.setEmailId(emailDetail[0]);
			email.setIsRead(Consts.EMAIL_READ);
			if (emailMapper.updateEmailStatus(email) != Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(EmailError.SHOW_DETAIL_FAILD);
				return result;
			}
		}
		Email email = emailMapper.findEmailById(emailDetail[0]);
		if (email != null) {
			detail.put("email", email);
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(EmailError.SHOW_DETAIL_FAILD);
		}
		return result;
	}

	@Override
	public MJSONObject batchDeleteEmail(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer type = jParams.getInteger("type");
		Integer[] emailIds = jParams.getObject("emailIds", Integer[].class);
		if (type != null && emailIds != null) {
			if (emailIds.length != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("array", emailIds);
				if (type == Consts.EMAIL_TYPE_RECEIVE) {
					map.put("receiveDel", Consts.EMAIL_IS_DELETE);
					if (emailMapper.batchUpdateReceiveEmailDelStatus(map) == emailIds.length) {
						result.setErrorCode(ErrorCode.SUCCESS);
					} else {
						result.setErrorCode(EmailError.DEL_EMAIL_FAILD);
					}
				} else {
					map.put("sendDel", Consts.EMAIL_IS_DELETE);
					if (emailMapper.batchUpdateSendEmailDelStatus(map) == emailIds.length) {
						result.setErrorCode(ErrorCode.SUCCESS);
					} else {
						result.setErrorCode(EmailError.DEL_EMAIL_FAILD);
					}
				}
			} else {
				result.setErrorCode(EmailError.SELECTED_ZERO);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject loadUnreadEmailsForMain(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		String token = jParams.getString("token");
		List<Email> emails = emailMapper.findReceiveUnreadEmails(globalContext.getUserToken().get(token).getUserId());
		detail.put("emails", emails);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

}
