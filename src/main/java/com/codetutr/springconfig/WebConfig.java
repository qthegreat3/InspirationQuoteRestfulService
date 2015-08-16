package com.codetutr.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.codetutr.controller.QuoteDAO;
import javax.sql.DataSource; 
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.codetutr.controller.QuoteDAOImpl;

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
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");    	
    	dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/inspiringquotedb");
    	dataSource.setUsername("root");
    	dataSource.setPassword("greatness");
    	
    	return dataSource;
    }
    
    @Bean
    public QuoteDAO getQuoteDAO(){
    	return new QuoteDAOImpl(getDataSource());
    }
}
