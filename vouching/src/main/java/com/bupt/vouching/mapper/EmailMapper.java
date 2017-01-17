package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

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

	/**
	 * 修改邮件状态
	 * 
	 * @param email
	 * @return
	 */
	public Integer updateEmailStatus(Email email);

	/**
	 * 通过ID查询邮件
	 * 
	 * @param integer
	 * @return
	 */
	public Email findEmailById(Integer integer);

	/**
	 * 批量修改收到邮件的删除状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer batchUpdateReceiveEmailDelStatus(Map<String, Object> map);

	/**
	 * 批量修改发送邮件的删除状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer batchUpdateSendEmailDelStatus(Map<String, Object> map);

	/**
	 * 查询所有未读的收件信
	 * 
	 * @param userId
	 * @return
	 */
	public List<Email> findReceiveUnreadEmails(Integer receiveId);
}
