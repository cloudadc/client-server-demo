package io.cloudadc.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
		.httpBasic(withDefaults())
		.formLogin(withDefaults());
		return http.build();
	}
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		
		UserDetails kylin = User.withDefaultPasswordEncoder()
				.username("kylin")
				.password("default")
				.roles("ROLE")
				.build();
		
		UserDetails user1 = User.withDefaultPasswordEncoder()
				.username("user1")
				.password("default")
				.roles("ROLE")
				.build();
		
		UserDetails user2 = User.withDefaultPasswordEncoder()
				.username("user2")
				.password("default")
				.roles("ROLE")
				.build();
	
		
		return new InMemoryUserDetailsManager(kylin, user1, user2);
	}
	

	
}
