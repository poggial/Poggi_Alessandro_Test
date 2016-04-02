package com.faac.soa.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@ComponentScan("com.facc.soa")
public class WebServiceConfiguration extends WsConfigurerAdapter {

	@Bean(name = "users")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usersSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("UsersPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://faac.com/soa/ws/users");
		wsdl11Definition.setSchema(usersSchema);
		return wsdl11Definition;
	}
@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
	PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
	validatingInterceptor.setXsdSchema(usersSchema());
	validatingInterceptor.setValidateRequest(true);
	validatingInterceptor.setValidateResponse(true);
	interceptors.add(validatingInterceptor);

	interceptors.add(new PayloadLoggingInterceptor());
	}
	@Bean
	public XsdSchema usersSchema() {
		return new SimpleXsdSchema(new ClassPathResource("user.xsd"));
	}
	
}