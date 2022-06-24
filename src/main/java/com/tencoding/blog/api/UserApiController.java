package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	// 기본 데이터 파싱 전략 key=value
	// application/x-www-form-urlencoded;charset=UTF-8 // key=value
	public ResponseDto<Integer> save(User user) { // @RequestBody 넣으면 JSON형식으로 받겠다는 뜻
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
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
