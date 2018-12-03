package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.dao.UserInfoRepository;
import com.example.demo.dto.UserInfo;

@Component
public class UserPopulator implements ApplicationRunner{

	@Autowired UserInfoRepository rep;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		UserInfo obj= new UserInfo();
		obj.setUsername("Payal");
		obj.setPassword("$2a$11$hmk0Liz1ql6Wihkd6/12Ke9c7TCfmSavzmAYbfM8X39I7csDBgHJO");
		obj.setRoles(Arrays.asList("user","admin"));
		rep.save(obj);
	}

}
