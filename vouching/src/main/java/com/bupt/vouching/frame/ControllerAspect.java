package com.bupt.vouching.frame;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.type.error.UserError;
import com.bupt.vouching.util.Utils;

/**
 * 控制层切面类
 * 
 * @author Hogan
 * 
 */
@Aspect
@Component
public class ControllerAspect {

	private Logger logger = Logger.getLogger(ControllerAspect.class);
	
	@Resource
	private GlobalContext globalContext;

	/**
	 * 指定的方法进行token校验
	 * 
	 * @param pjp
	 * @param tv
	 * @return
	 */
	@Before("within(com.bupt.vouching.controller..*)")
	public void TokenValid(JoinPoint jp) throws Exception {
		String signatureName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		String token = null;
		if(args!=null && args.length>0){
			if (jp.getArgs()[0] instanceof String) {
				token = (String) jp.getArgs()[0];
			} else if (jp.getArgs()[0] instanceof JSONObject) {
				if (!"adminLogin".equals(signatureName) && !"frontLogin".equals(signatureName)) {
					JSONObject jo = (JSONObject) jp.getArgs()[0];
					token = jo.getJSONObject(Consts.LABEL_PARAMS).getString(Consts.LABEL_TOKEN);
				} else {
					return;
				}
			} else {
				return;
			}
			logger.info("token标签为: " + token);
			if (Utils.isNullOrBlank(token)||!globalContext.getUserToken().containsKey(token)) {
				throw new SecurityException(UserError.NO_ACCESS_PRIVILEGE + "");
			} else {
				Date logicTime = new Date();
				globalContext.getUserToken().get(token).setLogicTime(logicTime);
				logger.info("用户操作的时间为:" + DateFormatUtils.format(logicTime,Consts.DATE_LOGICTIME_PATTERN));
			}
		}
	}
	
	@Before("within(com.bupt.vouching.controller..*)")
	public void joinedExamValidate(JoinPoint jp) throws Exception{
		
	}
	
}
