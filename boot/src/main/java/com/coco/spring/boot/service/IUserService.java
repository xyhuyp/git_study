package com.coco.spring.boot.service;

import java.util.List;

import com.coco.spring.boot.entity.UserInfo;

public interface IUserService {
	List<UserInfo> loadAll();

	UserInfo findOne(Long id);

	List<UserInfo> find(UserInfo userinfo);

	boolean save(UserInfo userinfo);

	boolean delete(UserInfo userinfo);

	boolean delete(Long id);
	
	boolean update(UserInfo userinfo);
}
