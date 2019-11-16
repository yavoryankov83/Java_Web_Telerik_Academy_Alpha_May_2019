package com.telerikacademy.springdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.validation.Validator;

@Configuration
public class ThymeleafConfig {

//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//        templateResolver.setPrefix("classpath:templates/");
//        templateResolver.setSuffix(".html");
//        return templateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.setTemplateEngineMessageSource(messageSource());
//        return templateEngine;
//    }
//
//    @Bean
//    public ThymeleafViewResolver viewResolver() {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setOrder(1);
//        return viewResolver;
//    }
//
//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }
//
//    @Bean
//    public Validator validator() {
//        final LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
//        factory.setValidationMessageSource(messageSource());
//        return factory;
//    }
}
