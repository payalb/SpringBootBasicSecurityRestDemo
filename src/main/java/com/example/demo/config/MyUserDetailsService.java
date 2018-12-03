package com.example.demo.config;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.dao.UserInfoRepository;
import com.example.demo.dto.UserInfo;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserInfoRepository rep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userInfo = rep.findById(username);
		if (!userInfo.isPresent()) {
			throw new RuntimeException("Invalid login details!!");
		}
		UserInfo user = userInfo.get();
		for(String role: user.getAuthorities()) {
			System.out.println(new SimpleGrantedAuthority(role));
		}
		return new User(user.getUsername(), user.getPassword(), true, true, true, true,
				user.getAuthorities().stream().map(x -> new SimpleGrantedAuthority(x)).collect(Collectors.toList()));
	}

}
