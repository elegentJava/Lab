package com.bupt.vouching.mapper;

import java.util.List;
import java.util.Map;

import com.bupt.vouching.bean.TeachResource;

/**
 * 教学资源数据层接口
 * 
 * @author Hogan
 * 
 */
public interface TeachResourceMapper {

	/**
	 * 保存一个上传文件
	 * 
	 * @param teachResource
	 * @return
	 */
	public Integer saveTeachResource(TeachResource teachResource);

	/**
	 * 根据文件路径删除文件
	 * 
	 * @param path
	 * @return
	 */
	public Integer deleteResourceByPath(String path);

	/**
	 * 通过ID查询资源信息
	 * 
	 * @param trid
	 * @return
	 */
	public TeachResource findResourceById(Integer trid);

	/**
	 * 修改资源的状态
	 * 
	 * @param map
	 * @return
	 */
	public Integer updateStatus(Map<String, Object> map);

	/**
	 * 通过类型查询资源信息
	 * 
	 * @param type
	 * @return
	 */
	public List<TeachResource> findResourcesByType(Integer type);

}
