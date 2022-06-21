package com.tencoding.blog.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@RestController
public class DummyControllerTest2 {

	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/dummy/users2")
	public List<User> researchAllUsers() {
		
		return userRepository.findAll();
	}
	
	
	@PostMapping("/dummy/users2")
	public String createUsers(@RequestBody User user) {
		
		user.setRole(RoleType.ADMIN);
		
		userRepository.save(user);
		return "회원가입 완료";
	}
	
	
	@GetMapping("/dummy/users2/{id}")
	public User userDetail(@PathVariable int id) {
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			
			return new IllegalArgumentException("해당 유저는 없슴");
			
		});
		
		return user;
		
	}
	
	@Transactional
	@PutMapping("/dummy/user2/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {

		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저 없음");
		});
		
		userEntity.setPassword(reqUser.getPassword());
		userEntity.setEmail(reqUser.getEmail());
		
		
		return null;
	}
	
	@DeleteMapping("/dummy/users/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);	
		} catch (Exception e) {
			return "해당 유저 없음";
		}
		return "삭제";
	}
	
//	@GetMapping("/dummy/users")
//	public Page<User> page(@PageableDefault(size = 2, sort = "id", 
//			direction = Direction.DESC) Pageable pageble){
//		
//		Page<User> pageUser = userRepository.findAll(pageble);
//		
//		return pageUser;
//	}
	
	
	
	
	
}
