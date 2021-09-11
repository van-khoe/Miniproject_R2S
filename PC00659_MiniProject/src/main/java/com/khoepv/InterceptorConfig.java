package com.khoepv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.khoepv.interceptor.GlobalInterceptor;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	GlobalInterceptor gi;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(gi)
		.addPathPatterns("/**")
		.excludePathPatterns("/rest/**","/admin/**","/assets/**");
	}
}
