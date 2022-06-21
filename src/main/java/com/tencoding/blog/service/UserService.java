package com.tencoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@Service // IoC 등록
public class UserService {
	
	
	/**
	 * 
	 *  서비스가 필요한 이유
	 * 	
	 * 
	 * 
	 */
	
	
	// DI 의존 주입
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public int saveUser(User user) {
		
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
			
		
	}
}
