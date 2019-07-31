package com.demo.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.user.IUserDao;
import com.demo.model.user.User;
import com.demo.service.user.IUserService;

/**
 * 用户基础信息Service实现类
 * 
 * @author chenjian
 * @createDate 2019-01-17
 */
@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}

	@Override
	public User findByCondition(Map<String, Object> params) {
		return userDao.findByCondition(params);
	}

	@Override
	public List<User> findAll(Map<String, Object> params) {
		return userDao.findAll(params);
	}

	@Override
	public void save(User entity) {
		userDao.save(entity);
	}

	@Override
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	public void delete(String id) {
		userDao.delete(id);
	}
}