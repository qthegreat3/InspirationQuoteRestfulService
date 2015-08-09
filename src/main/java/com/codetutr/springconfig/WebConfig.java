package com.codetutr.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.apache.commons.dbcp.BasicDataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.codetutr.controller")

public class WebConfig {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver =
                    new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public DataSource getDataSource(){
    	DataSource dataSource = new DataSource();
    	dataSource.driverClassName("com.mysql.jdbc.Driver");    	
    	dataSource.setUrl("jdbc:mysql@localhost:3306/inspiringquotedb");
    	dataSource.setUsername("root");
    	dataSource.setPassword("greatness");
    	
    	return dataSource;
    }
    
    @Bean
    public QuoteDAO getQuoteDAO(){
    	return new QuoteDAOImpl(getDataSource());
    }
}
