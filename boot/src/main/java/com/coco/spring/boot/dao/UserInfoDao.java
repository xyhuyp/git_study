package com.coco.spring.boot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.coco.spring.boot.entity.UserInfo;

@Component
public interface UserInfoDao extends CrudRepository<UserInfo, Long>{

}
