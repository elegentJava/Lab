package com.bupt.vouching.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.bupt.vouching.frame.Consts;
import com.bupt.vouching.frame.GlobalContext;
import com.bupt.vouching.frame.MJSONObject;
import com.bupt.vouching.mapper.ChapterMapper;
import com.bupt.vouching.mapper.ClassMapper;
import com.bupt.vouching.mapper.GlossaryMapper;
import com.bupt.vouching.mapper.TeachResourceMapper;
import com.bupt.vouching.mapper.UserMapper;
import com.bupt.vouching.service.AdminService;
import com.bupt.vouching.type.PageSize;
import com.bupt.vouching.type.ResourceType;
import com.bupt.vouching.type.error.AdminError;
import com.bupt.vouching.type.error.ErrorCode;
import com.bupt.vouching.type.error.ResourceError;
import com.bupt.vouching.type.error.UserError;
import com.bupt.vouching.type.excel.ChapterTemplate;
import com.bupt.vouching.type.excel.GlossaryTemplate;
import com.bupt.vouching.type.excel.UserTemplate;
import com.bupt.vouching.util.POIUtils;
import com.bupt.vouching.util.Utils;
import com.bupt.vouching.util.page.PageHelper;
import com.bupt.vouching.util.page.PageInfo;
import com.bupt.vouching.bean.Chapter;
import com.bupt.vouching.bean.Class;
import com.bupt.vouching.bean.Glossary;
import com.bupt.vouching.bean.TeachResource;
import com.bupt.vouching.bean.User;

/**
 * 管理员业务层实现
 * 
 * @author Hogan
 * 
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private ClassMapper classMapper;
	
	@Resource 
	private UserMapper userMapper;
	
	@Resource
	private TeachResourceMapper teachResourceMapper;
	
	@Resource
	private ChapterMapper chapterMapper;
	
	@Resource
	private GlossaryMapper glossaryMapper;

	@Resource
	private POIUtils poiUtils;
	
	@Resource
	private GlobalContext globalContext;
	
	@Override
	public MJSONObject loadUserList(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		Integer role = jParams.getInteger("role");
		Integer pageNum = jParams.getInteger("pageNum");
		if (role != null && pageNum != null) {
			PageHelper.startPage(pageNum, PageSize.USER_LIST_RECORD);
			List<User> users = null;
			if (Consts.ROLE_STUDENT == role) {
				users = userMapper.findUserAndClassByRole(role);
			} else {
				users = userMapper.findSingleUsersByRole(role);
			}
			PageInfo<User> pageInfo = new PageInfo<User>(users);
			detail.put("users", pageInfo.getList());
			result.setPageInfo(pageInfo);
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject resetPassword(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String userId = jParams.getString("userId");
		if (userId != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("password", Consts.DEFAULT_PASSWORD);
			if (userMapper.updatePassword(map) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(UserError.RESET_PASSWORD_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject deleteSinglerUser(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer userId = jParams.getInteger("userId");
		if (userId != null) {
			if (userMapper.deleteSingleUserById(userId) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(UserError.DELETE_USER_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject loadClasses(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<Class> classes = classMapper.findClassByStatus(Consts.ACTIVE);
		detail.put("classes", classes);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
	@Override
	public MJSONObject loadTeacherClasses(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		List<Class> classes = classMapper.findClassByUserRole(Consts.ROLE_TEACHER);
		detail.put("classes", classes);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public MJSONObject addUser(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String account = jParams.getString("account");
		String email = jParams.getString("email");
		String name = jParams.getString("name");
		Integer sex = jParams.getInteger("sex");
		Integer clas = jParams.getInteger("clas");
		Integer role = jParams.getInteger("role");
		if (role != null && sex != null && clas != null) {
			if (!Utils.isNullOrBlank(account)) {
				if (userMapper.findUserByAccount(account) == null) {
					if (!Utils.isNullOrBlank(name)) {
						if (!Utils.isNullOrBlank(email)) {
							if (Utils.EmailIsValid(email)) {
								User user = new User();
								user.setAccount(account);
								if(0 == clas){
									clas = Consts.DEFAULT_CLASS_ID;
								}
								user.setClassId(clas);
								user.setEmail(email);
								user.setIsOnline(Consts.USER_NOT_ONLINE);
								user.setName(name);
								user.setRole(role);
								user.setSex(sex);
								user.setPassword(Consts.DEFAULT_PASSWORD);
								if (userMapper.saveUser(user) == Consts.DATA_SINGLE_SUCCESS) {
									result.setErrorCode(ErrorCode.SUCCESS);
								} else {
									result.setErrorCode(UserError.ADD_USER_FAILD);
								}
							} else {
								result.setErrorCode(UserError.EMAIL_INVALID);
							}
						} else {
							result.setErrorCode(UserError.EMAIL_IS_NULL);
						}
					} else {
						result.setErrorCode(UserError.NAME_IS_NULL);
					}
				} else {
					result.setErrorCode(UserError.ACCOUNT_EXISTED);
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
	public MJSONObject batchInsertUsers(Map<String, Object> params,HttpServletRequest request) {
		MJSONObject result = new MJSONObject();
		String token = (String) params.get("token");
		Integer role = Integer.parseInt((String) params.get("role"));
		if (globalContext.getUserToken().get(token) != null) {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> ite = multiRequest.getFileNames();
				while (ite.hasNext()) {
					MultipartFile file = multiRequest.getFile(ite.next());
					if (file != null) {
						try {
							Map<String, String> accountValidateMap = new HashMap<String,String>();
							List<User> users = poiUtils.read(file, UserTemplate.DEFAULT, User.class);
							for (User user : users) {
								user.setPassword(Consts.DEFAULT_PASSWORD);
								user.setIsOnline(Consts.USER_NOT_ONLINE);
								user.setClassId(Consts.DEFAULT_CLASS_ID);
								user.setRole(role);
								accountValidateMap.put(user.getAccount(), user.getAccount());
								if (userMapper.findUserByAccount(user.getAccount()) != null) {
									result.setErrorCode(UserError.ACCOUNT_EXISTED);
									return result;
								} 
							}
							if (accountValidateMap.size() == users.size()) {
								if (userMapper.batchInsert(users) == users.size()) {
									result.setErrorCode(ErrorCode.SUCCESS);
								} else {
									result.setErrorCode(UserError.USER_BATCH_INSERT_FAILD);
								}
							} else {
								result.setErrorCode(UserError.ACCOUNT_EXISTED);
							}
						} catch (Exception e) {
							log.error("上传文件失败!", e);
							result.setErrorCode(ResourceError.UPLOAD_FAILD);
						}
					}
				}
			}
		} else {
			result.setErrorCode(ErrorCode.FAILD);
		}
		return result;
	}

	@Override
	public MJSONObject loadClassList(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		Integer pageNum = jParams.getInteger("pageNum");
		if (pageNum != null) {
			PageHelper.startPage(pageNum, PageSize.CLASS_LIST_RECORD);
			List<Class> classes = classMapper.findAllClass();
			PageInfo<Class> pageInfo = new PageInfo<Class>(classes);
			detail.put("classes", pageInfo.getList());
			result.setDetail(detail);
			result.setPageInfo(pageInfo);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject deleteSingleClass(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer classId = jParams.getInteger("classId");
		if (classId != null) {
			if (classMapper.deleteSingle(classId) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(AdminError.CLASS_DEL_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject updateClassStatus(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer classId = jParams.getInteger("classId");
		Integer isActive = jParams.getInteger("isActive");
		if (classId != null && isActive != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("classId", classId);
			map.put("isActive", isActive);
			if (classMapper.updateClassSatatus(map) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(AdminError.CLASS_UPDATE_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject batchDeleteClass(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer[] classIds = jParams.getObject("classIds", Integer[].class);
		if (classIds != null) {
			if (classIds.length > 0) {
				if (classMapper.batchDelete(classIds) == classIds.length) {
					result.setErrorCode(ErrorCode.SUCCESS);
				} else {
					result.setErrorCode(AdminError.CLASS_DEL_FAILD);
				}
			} else {
				result.setErrorCode(AdminError.SELECTED_ONE_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject addClass(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		String className = jParams.getString("className");
		String bak = jParams.getString("bak");
		Integer isActive = jParams.getInteger("isActive");
		if (isActive != null) {
			if (!Utils.isNullOrBlank(className)) {
				if (classMapper.findCLassByName(className) == null) {
					Class clas = new Class();
					clas.setBak(bak);
					clas.setClassName(className);
					clas.setIsActive(isActive);
					if(classMapper.addClass(clas) == Consts.DATA_SINGLE_SUCCESS){
						result.setErrorCode(ErrorCode.SUCCESS);
					}else{
						result.setErrorCode(AdminError.CLASS_ADD_FAILD);
					}
				} else {
					result.setErrorCode(AdminError.CLASS_NAME_EXISTED);
				}
			} else {
				result.setErrorCode(AdminError.CLASS_NAME_IS_NULL);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject batchDeleteUsers(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer[] userIds = jParams.getObject("userIds", Integer[].class);
		if (userIds != null) {
			if (userIds.length > 0) {
				if (userMapper.batchDelete(userIds) == userIds.length) {
					result.setErrorCode(ErrorCode.SUCCESS);
				} else {
					result.setErrorCode(AdminError.USER_DELETE_FAILD);
				}
			} else {
				result.setErrorCode(AdminError.SELECTED_ONE_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject loadResourceList(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		JSONObject detail = new JSONObject();
		Integer pageNum = jParams.getInteger("pageNum");
		Integer type = jParams.getInteger("type");
		if (pageNum != null && type != null) {
			PageHelper.startPage(pageNum, PageSize.RESOURCE_LIST_RECORD);
			List<TeachResource> resources = teachResourceMapper.findResourcesByType(type);
			PageInfo<TeachResource> pageInfo = new PageInfo<TeachResource>(resources);
			detail.put("resources", resources);
			result.setDetail(detail);
			result.setPageInfo(pageInfo);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public MJSONObject dealSingleResource(JSONObject jParams) {
		MJSONObject result = new MJSONObject();
		Integer trid = jParams.getInteger("trid");
		if (trid != null) {
			TeachResource resource = teachResourceMapper.findResourceById(trid);
			if(resource != null){
				String path = resource.getPath();
				if (Consts.RESOURCE_UN_DEAL == resource.getFlag()) {
					switch (ResourceType.byId(resource.getType())) {
					case CHAPTER:
						List<Chapter> chapters = poiUtils.read(new File(path), ChapterTemplate.DEFAULT, Chapter.class);
						if (chapters != null) {
							for(Chapter e : chapters){
								e.setIsActive(Consts.INACTIVE);
							}
							if (chapterMapper.batchInsert(chapters) != chapters.size()) {
								result.setErrorCode(AdminError.RESOURCE_DEAL_FAILD);
								return result;
							}
						} else {
							result.setErrorCode(AdminError.RESOURCE_DEAL_FAILD);
							return result;
						}
						break;
					case GLOSSARY:
						List<Glossary> glossaries = poiUtils.read(new File(path), GlossaryTemplate.DEFAULT, Glossary.class);
						if (glossaries != null) {
							if (glossaryMapper.batchInsert(glossaries) != glossaries.size()) {
								result.setErrorCode(AdminError.RESOURCE_DEAL_FAILD);
								return result;
							}
						} else {
							result.setErrorCode(AdminError.RESOURCE_DEAL_FAILD);
							return result;
						}
						break;
					default:
						break;
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("trid", resource.getTrid());
					map.put("flag", Consts.RESOURCE_DEAL);
					if (teachResourceMapper.updateStatus(map) == Consts.DATA_SINGLE_SUCCESS) {
						result.setErrorCode(ErrorCode.SUCCESS);
					} else {
						result.setErrorCode(AdminError.RESOURCE_DEAL_FAILD);
					}
				} else {
					result.setErrorCode(AdminError.RESOURCE_ALREADY_DEAL);
				}
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

}
