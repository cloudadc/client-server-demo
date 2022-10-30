package io.cloudadc.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthServerResource {
		
	@RequestMapping({"/authorize"})
	public ResponseEntity<String> authorize(HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		response.addHeader("X-Forwarded-Status", "200");
		response.addHeader("X-Forwarded-User", authentication.getName());
		return ResponseEntity.ok("success");
		
	}
	
	@RequestMapping({"/admin"})
	public ResponseEntity<String> admin() {
		return ResponseEntity.ok("admin");
	}
	
	@RequestMapping({"/auth/login"})
	public ResponseEntity<String> user() {
		return ResponseEntity.ok("user");
	}
	
	@RequestMapping({"/login.html"})
	public ResponseEntity<String> login() {
		return ResponseEntity.ok("login");
	}
	
}
