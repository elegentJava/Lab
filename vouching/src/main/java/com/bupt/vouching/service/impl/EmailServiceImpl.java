package com.bupt.vouching.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
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
			if (globalContext.getUserInExamMap().get(token) == null ) {
			Integer emailType = jParams.getInteger("emailType");
			Integer pageNum = jParams.getInteger("pageNum");
			if (emailType != null && pageNum!= null) {
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
		} else {
			result.setErrorCode(EmailError.USER_IN_EXAM);
		}
		return result;
	}

	@Override
	public MJSONObject loadUsersInClass(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		Integer classId = jParams.getInteger("classId");
		if (classId != null) {
			List<User> users = userMapper.findUsersByClassId(classId);
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
						if (receiver != null) {
							Email email = new Email();
							email.setContent(content);
							email.setDate(new Date());
							email.setIsRead(Consts.EMAIL_UNREAD);
							email.setReceiveDel(Consts.EMAIL_IS_NOT_DELETE);
							email.setReceiveEmail(receiver.getEmail());
							email.setReceiveName(receiver.getName());
							email.setReceiveId(receiver.getUserId());
							email.setSendDel(Consts.EMAIL_IS_NOT_DELETE);
							email.setSendEmail(sender.getEmail());
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

}
