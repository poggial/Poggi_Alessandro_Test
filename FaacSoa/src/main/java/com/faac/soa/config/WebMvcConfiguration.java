package com.faac.soa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.faac.soa")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{
	@Bean
	public VelocityConfigurer velocityConfig() {
	    VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
	    velocityConfigurer.setResourceLoaderPath("/resources/");
	    return velocityConfigurer;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		VelocityViewResolver viewResolver = new VelocityViewResolver();
		
		viewResolver.setViewClass(VelocityView.class);
		viewResolver.setCache(false);
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".html");
		viewResolver.setExposeSpringMacroHelpers(true);

		registry.viewResolver(viewResolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
}