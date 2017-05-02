package com.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.validators.ProductValidator;
 
@Configuration
public class WebConfiguration {
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
    
    @Bean(name="messageSource")
    public MessageSource messageSource(){
    	
    	ReloadableResourceBundleMessageSource reloadMessage = new ReloadableResourceBundleMessageSource();
    	
    	reloadMessage.setBasename("classpath:/messages");
    	
    	reloadMessage.setDefaultEncoding("UTF-8");
    	
    	reloadMessage.setCacheSeconds(0);
    	
    	return reloadMessage;
    	
    }
    
    //TODO: investigate whether there is a better way to wire up validators and not cause a proliferation
    @Bean(name="com.validators.ProductValidator")
    public Validator validator(){
    	Validator validator = getValidator();
    	return validator;
    }
    
    @Bean(name="localValidator")
    public Validator getValidator(){
    	LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    	
    	validator.setValidationMessageSource(messageSource());
    	
    	return validator;
    }
    
}
