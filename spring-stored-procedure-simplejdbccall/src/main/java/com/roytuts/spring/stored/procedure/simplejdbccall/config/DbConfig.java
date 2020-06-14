package com.roytuts.spring.stored.procedure.simplejdbccall.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Configuration
public class DbConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().driverClassName(environment.getProperty("spring.datasource.driverClassName"))
				.url(environment.getProperty("spring.datasource.url"))
				.username(environment.getProperty("spring.datasource.username"))
				.password(environment.getProperty("spring.datasource.password")).build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "simpleJdbcCall1")
	public SimpleJdbcCall simpleJdbcCall1(DataSource dataSource) {
		return new SimpleJdbcCall(dataSource);
	}

	@Bean(name = "simpleJdbcCall2")
	public SimpleJdbcCall simpleJdbcCall2(JdbcTemplate jdbcTemplate) {
		return new SimpleJdbcCall(jdbcTemplate);
	}

}
