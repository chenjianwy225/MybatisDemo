package com.demo.service;

import java.util.List;
import java.util.Map;

/**
 * 基础Service接口
 * 
 * @author chenjian
 *
 * @param <T>
 * @createDate 2019-01-17
 */
public interface IBaseService<T> {

	/**
	 * 根据id获取信息
	 * 
	 * @param clazz
	 *            实体类
	 * @param id
	 *            实体类主键编号
	 * @return
	 */
	T findById(String id);

	/**
	 * 根据条件获取信息
	 * 
	 * @param clazz
	 *            实体类
	 * @param param
	 *            条件参数
	 * @return
	 */
	T findByCondition(Map<String, Object> params);

	/**
	 * 获取所有信息
	 * 
	 * @param clazz
	 *            实体类
	 * @param params
	 *            条件参数
	 * @return
	 */
	List<T> findAll(Map<String, Object> params);

	/**
	 * 保存信息
	 * 
	 * @param entity
	 *            实体类
	 */
	void save(T entity);

	/**
	 * 修改信息
	 * 
	 * @param entity
	 *            实体类
	 */
	void update(T entity);

	/**
	 * 根据id删除信息
	 * 
	 * @param clazz
	 *            实体类
	 * @param id
	 *            实体类主键编号
	 */
	void delete(String id);
}