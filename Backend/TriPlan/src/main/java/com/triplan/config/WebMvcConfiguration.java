package com.triplan.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = {"com.triplan.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		단순 페이지 이동 
		registry.addViewController("/").setViewName("index");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
}
