package com.coco.spring.boot.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coco.spring.boot.dao.UserInfoDao;
import com.coco.spring.boot.entity.UserInfo;
import com.coco.spring.boot.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserInfoDao userInfoDao;
	
	@Override
	public List<UserInfo> loadAll() {
		List<UserInfo> resVal  = new ArrayList<>();
		Iterable<UserInfo> user =  userInfoDao.findAll();
		for (UserInfo userInfo : user) {
			resVal.add(userInfo);
		}
		return resVal;
	}

	@Override
	public UserInfo findOne(Long id) {
		return userInfoDao.findOne(id);
	}

	@Override
	public List<UserInfo> find(UserInfo userinfo) {
		return null;
	}

	@Override
	public boolean save(UserInfo userinfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(UserInfo userinfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserInfo userinfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
