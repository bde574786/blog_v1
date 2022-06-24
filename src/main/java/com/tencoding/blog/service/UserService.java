package com.tencoding.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@Service // IoC 등록
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	// DI 의존 주입
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public int saveUser(User user) {
		
		try {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			
			
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;			
	
	}
	
//	@Transactional(readOnly = true)
//	public User login(User user) {
//		// repository select 요청
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
	
	
}
