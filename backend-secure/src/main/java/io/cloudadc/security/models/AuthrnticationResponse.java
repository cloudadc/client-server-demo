package io.cloudadc.security.models;

public class AuthrnticationResponse {
	
	private final String jwt;
	
	public AuthrnticationResponse(String jwt) {
		this.jwt = jwt;
	}
	
	public String getJwt() {
		return jwt;
	}

}
