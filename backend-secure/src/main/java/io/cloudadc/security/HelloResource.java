package io.cloudadc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.cloudadc.security.models.AuthrnticationRequest;
import io.cloudadc.security.models.AuthrnticationResponse;
import io.cloudadc.security.service.MyUserDetailsService;
import io.cloudadc.security.utils.JWTUtils;

@RestController
public class HelloResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtils jwtUtils;

	
	@RequestMapping({"/hello"})
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST )
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthrnticationRequest suthrnticationRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(suthrnticationRequest.getUsername(),suthrnticationRequest.getPassword()));
		} catch (AuthenticationException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetials = userDetailsService.loadUserByUsername(suthrnticationRequest.getUsername());
		
		final String jwt = jwtUtils.generateToken(userDetials);
		
		return ResponseEntity.ok(new AuthrnticationResponse(jwt));
	}

}
