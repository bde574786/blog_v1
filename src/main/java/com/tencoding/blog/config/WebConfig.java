package com.tencoding.blog.config;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		WebMvcConfigurer.super.addResourceHandlers(registry);
		 
		registry.addResourceHandler("/upload/**") // URL 패턴 /upload/ -> 낚아 챔
		.addResourceLocations("file:///" + uploadFolder) // 실제 물리적인 경로
		.setCachePeriod(60 * 10 * 6) // 캐시의 지속시간 설정(초)
		.resourceChain(true) // 리소스 찾는 것을 최적화 하기 위해서
		.addResolver(new PathResourceResolver());
	
	}
	
	@Bean
	// 메서드를 수행시킬 때 힙 영역에 생성시킴
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {

		FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new XssEscapeServletFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");

		return filterRegistrationBean;

	}

}
