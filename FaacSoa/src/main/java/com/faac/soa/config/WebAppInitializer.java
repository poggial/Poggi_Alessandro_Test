package com.faac.soa.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		servletContext.addListener(new ContextLoaderListener(ctx));
		ctx.register(WebServiceConfiguration.class, WebMvcConfiguration.class, ApplicationContextConfig.class,
				SecurityConfiguration.class);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher_mvc",
				new DispatcherServlet(ctx));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		ctx.setServletContext(servletContext);
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(ctx);
		servlet.setTransformWsdlLocations(true);
		Dynamic dynamic = servletContext.addServlet("dispatcher_ws", servlet);
		dynamic.addMapping("/ws/*");
		dynamic.setLoadOnStartup(1);
	}

}