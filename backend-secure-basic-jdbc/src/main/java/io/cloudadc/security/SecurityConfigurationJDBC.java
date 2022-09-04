package io.cloudadc.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfigurationJDBC  {
	
	@Autowired
	public DataSource dataSource;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
		.httpBasic(withDefaults())
		.formLogin(withDefaults());
		return http.build();
	}
	
	@Bean
	public JdbcUserDetailsManager userDetailsService() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		return jdbcUserDetailsManager;
	}
	
	

	
}
