package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserInfo;

@Transactional
public interface UserInfoRepository extends CrudRepository<UserInfo, String>{

}
