package com.bupt.vouching.mapper;

import java.util.List;

import com.bupt.vouching.bean.Email;

/**
 * 邮件数据层接口
 * 
 * @author Hogan
 * 
 */
public interface EmailMapper {

	/**
	 * 查询所有未删除的收件信
	 * 
	 * @param receiveId
	 * @return
	 */
	public List<Email> findReceiveEmails(int receiveId);

	/**
	 * 查询所有未删除的发件信
	 * 
	 * @param receiveId
	 * @return
	 */
	public List<Email> findSendEmails(int sendId);

	/**
	 * 保存邮件信息
	 * 
	 * @param email
	 * @return
	 */
	public Integer saveEmail(Email email);
}
