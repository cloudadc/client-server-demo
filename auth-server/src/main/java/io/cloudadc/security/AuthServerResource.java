package io.cloudadc.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthServerResource {
		
	@RequestMapping({"/authorize"})
	public String authorize(HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("name: " + authentication.getName());
		System.out.println("credentials: " + authentication.getCredentials());
		System.out.println("principal: " + authentication.getPrincipal());
		if(authentication.getName() == null || authentication.getName().length() == 0 || authentication.getName().contentEquals("anonymousUser")) {
			
		}
		return authentication.getName() + " " + authentication.getCredentials() + " " + authentication.getPrincipal();
	}
	
	@RequestMapping({"/admin"})
	public String admin() {
		return "admin";
	}
	
	@RequestMapping({"/user"})
	public String user() {
		return "user";
	}
	
	@RequestMapping({"/login"})
	public String login() {
		return "login";
	}
	
}
