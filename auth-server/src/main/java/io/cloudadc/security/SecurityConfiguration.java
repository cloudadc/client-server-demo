package io.cloudadc.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/admin/**")
            .hasRole("ADMIN")
            .antMatchers("/user/**")
            .hasRole("USER")
            .antMatchers("/login*")
            .authenticated()
            .and()
    		.httpBasic(withDefaults())
    		.formLogin(withDefaults());
		
		return http.build();
	}
	
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		
		UserDetails kylin = User.withUsername("kylin")
				.password(passwordEncoder().encode("default"))
				.roles("USER")
				.build();
		
		
		UserDetails user1 = User.withUsername("user1")
				.password(passwordEncoder().encode("default"))
				.roles("USER")
				.build();
		
		UserDetails user2 = User.withUsername("user2")
				.password(passwordEncoder().encode("default"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.withUsername("admin")
				.password(passwordEncoder().encode("default"))
				.roles("ADMIN")
				.build();
	
		
		return new InMemoryUserDetailsManager(kylin, user1, user2, admin);
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	

	
}
