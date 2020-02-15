package com.example.demo.jwt;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


/**
 * Utilities method to work with JWT.
 * 
 * @author PIAZZOLLAG
 *
 */
@Component
public class TokenProvider {

	protected static final String AUTHORITIES_KEY = "auth";

	public Authentication getAuthentication(String token) {
		Object customUserDetails = null;
		Collection<? extends GrantedAuthority> authorities = null;
		return new UsernamePasswordAuthenticationToken(customUserDetails, token, authorities);
	}

	public String createToken() {
		return "token";
	}


	public boolean validateToken(String jwt) {
		return true;
	}


}
