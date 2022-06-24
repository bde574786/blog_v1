package com.tencoding.blog.config;

import javax.annotation.security.PermitAll;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // 빈 등록(IoC)
@EnableWebSecurity // 시큐리티 필터로 등록이 된다.(필터 커스텀)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증처리를 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	// 1 . 비밀번호해시 처리
	@Bean // Ioc가 된다 (필요할 때 가져와서 사용하면 된다.)
	public BCryptPasswordEncoder encoderPW() {
		return new BCryptPasswordEncoder();
	}
	
	// 2 특정 주소 필터를 생성할 예정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/auth/**", "/", "/js/**", "/css/**","/image/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/auth/login_form");

		
	}
	
}
