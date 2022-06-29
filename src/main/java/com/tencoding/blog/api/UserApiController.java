package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager; // 미리 security에 올려놔야 함(오버라이드)
	

	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {
		userService.updateUser(user);
		
		// 강제로 Authentication 객체를 만들고 SecurityContext 안에 집어 넣으면 된다.
		
		// 1. Authentication 객체 생성
		// 2. AuthenticationManager 메모리에 올려서 Authenticate 메서드를 사용해서 Authentication 객체를 저장한다.
		// 3. 세션 안에 SecurityContextHolder.getContext().setAuthentication() Authentication 객체를 넣어주면 된다.
	
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
//	@PostMapping("/api/user")
//	public ResponseDto<Integer> save(@RequestBody User user) {
//		// DB (벨리데이션)
//		System.out.println("UserApiController 호출됨!!!");
//		user.setRole(RoleType.USER);
//
//		int result = userService.saveUser(user);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
//	}
//
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user) {
//		System.out.println("login 호출됨");
//		// 서비스한테 요청
//		// principal 접근 주체
//		User principal = userService.login(user);
//		// 접근 주체가 정상적으로 username, password 확인 (세션이라는 거대한 메모리에 저장)
//		if(principal != null) {
//			httpSession.setAttribute("principal", principal);	
//			System.out.println("세션 정보가 저장되었습니다.");
//		}
//		
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}

	
	
}
