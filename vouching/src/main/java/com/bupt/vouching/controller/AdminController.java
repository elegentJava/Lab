package com.bupt.vouching.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.BaseController;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.frame.RequestTemplate;
import com.bupt.vouching.frame.ResponseTemplate;
import com.bupt.vouching.service.AdminService;

@Controller
@RequestMapping("/admin/")
public class AdminController extends BaseController {

	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private AdminService adminService;
	
	/**
	 * 装载用户列表数据
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadUserList")
	public @ResponseBody JSONObject loadUserList(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.loadUserList(rt.getJParams());
		} catch (Exception e) {
			log.error("装载用户列表数据失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 重置密码
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("resetPassword")
	public @ResponseBody JSONObject resetPassword(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.resetPassword(rt.getJParams());
		} catch (Exception e) {
			log.error("重置密码失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 删除单个用户
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("deleteSinglerUser")
	public @ResponseBody JSONObject deleteSinglerUser(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.deleteSinglerUser(rt.getJParams());
		} catch (Exception e) {
			log.error("删除单个用户失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载添加用户页面的班级列表
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadClasses")
	public @ResponseBody JSONObject loadClasses(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.loadClasses(rt.getJParams());
		} catch (Exception e) {
			log.error("装载添加用户页面的班级列表失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 添加用户
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("addUser")
	public @ResponseBody JSONObject addUser(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.addUser(rt.getJParams());
		} catch (Exception e) {
			log.error("添加用户失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 批量添加用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("batchInsertUsers")
	public @ResponseBody JSONObject batchInsertUsers(HttpServletRequest request, HttpServletResponse response) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(request);
			detail = adminService.batchInsertUsers(rt.getParams(),request);
		} catch (Exception e) {
			log.error("批量添加用户失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 批量删除用户
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("batchDeleteUsers")
	public @ResponseBody JSONObject batchDeleteUsers(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.batchDeleteUsers(rt.getJParams());
		} catch (Exception e) {
			log.error("批量删除用户失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 装载班级信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadClassList")
	public @ResponseBody JSONObject loadClassList(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.loadClassList(rt.getJParams());
		} catch (Exception e) {
			log.error("装载班级信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 删除一个班级
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("deleteSingleClass")
	public @ResponseBody JSONObject deleteSingleClass(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.deleteSingleClass(rt.getJParams());
		} catch (Exception e) {
			log.error("删除一个班级失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 更新班级状态
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("updateClassStatus")
	public @ResponseBody JSONObject updateClassStatus(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.updateClassStatus(rt.getJParams());
		} catch (Exception e) {
			log.error("更新班级状态失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 批量删除班级
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("batchDeleteClass")
	public @ResponseBody JSONObject batchDeleteClass(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.batchDeleteClass(rt.getJParams());
		} catch (Exception e) {
			log.error("批量删除班级失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 添加班级信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("addClass")
	public @ResponseBody JSONObject addClass(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.addClass(rt.getJParams());
		} catch (Exception e) {
			log.error("添加班级信息失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载资源列表
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadResourceList")
	public @ResponseBody JSONObject loadResourceList(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.loadResourceList(rt.getJParams());
		} catch (Exception e) {
			log.error("装载资源列表失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 处理单个资源文件
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("dealSingleResource")
	public @ResponseBody JSONObject dealSingleResource(@RequestBody JSONObject jo) {
		MJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.dealSingleResource(rt.getJParams());
		} catch (Exception e) {
			log.error("处理单个资源文件失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
}
