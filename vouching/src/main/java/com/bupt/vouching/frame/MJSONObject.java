package com.bupt.vouching.frame;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.type.base.ErrorType;
import com.bupt.vouching.util.page.PageInfo;

/**
 * 在原有JSONObject的基础上做一些适合项目的加工
 * 
 * @author Hogan
 * 
 */
public class MJSONObject extends JSONObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认操作是成功的
	 */
	public MJSONObject() {
		setResult(Consts.RESULT_SUCCESS);
	}

	/**
	 * 设置错误信息
	 * 
	 * @param errorCode 错误码
	 * @return
	 */
	public Object setErrorCode(ErrorType errorCode) {
		return put(Consts.LABEL_ERROR_CODE, errorCode.getDescription());
	}

	/**
	 * 设置结果类型
	 * 
	 * @param result 结果码
	 * @return
	 */
	public Object setResult(String result) {
		return put(Consts.LABEL_RESULT, result);
	}

	/**
	 * 设置业务操作结果
	 * 
	 * @param detail 业务处理结果
	 * @return
	 */
	public Object setDetail(JSONObject detail) {
		return put(Consts.LABEL_DETAIL, detail);
	}

	/**
	 * 设置分页结果
	 * 
	 * @param pageInfo 分页信息对象
	 * @return
	 */
	public Object setPageInfo(@SuppressWarnings("rawtypes") PageInfo pageInfo) {
		JSONObject page = new JSONObject();
		page.put(Consts.PAGE_NUM, pageInfo.getPageNum());
		page.put(Consts.PAGE_HAVE_NEXT, pageInfo.isHasNextPage());
		page.put(Consts.PAGE_SUM, pageInfo.getPages());
		page.put(Consts.PAGE_RECORD_COUNT, pageInfo.getTotal());
		return put(Consts.PAGE_LABEL, page);
	}
}
