package com.tagdish.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value = "classpath:mysql.properties")
public class JDBCConfig {

	
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Value("${mysql.database.driver.class}")
	private String mysqlDriverClass;
	
	@Value("${mysql.database.url}")
	private String mysqlDatabaseUrl;
	
	
	@Value("${mysql.database.username}")
	private String mysqlUserName;
	
	@Value("${mysql.database.password}")
	private String mysqlPassword;			
	
	@Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mysqlDriverClass);
        dataSource.setUrl(mysqlDatabaseUrl);
        dataSource.setUsername(mysqlUserName);
        dataSource.setPassword(mysqlPassword);
         
        return dataSource;
    }	
}
