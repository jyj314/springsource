package com.study.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MapperScan("com.study.mapper")
@ComponentScan("com.study.service")
@Configuration
public class RootConfig {
	
		@Bean
		public DataSource dataSource() {
			HikariConfig hikariConfig = new HikariConfig();
			hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
			hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
			hikariConfig.setUsername("javadb");
			hikariConfig.setPassword("12345");
			
			HikariDataSource datasource = new HikariDataSource(hikariConfig);
			return datasource;
		}
		
		@Bean
		public SqlSessionFactory sqlSessionFactory() throws Exception{
			SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
			sqlSessionFactoryBean.setDataSource(dataSource());
			return sqlSessionFactoryBean.getObject();
		}
}
