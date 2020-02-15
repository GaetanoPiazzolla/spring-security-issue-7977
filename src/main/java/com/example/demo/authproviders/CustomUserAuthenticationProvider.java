package com.example.demo.authproviders;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@org.springframework.stereotype.Component // <- FIXME: if I remove this, everything works fine.
public class CustomUserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		System.out.println("Custom user auth provider");
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		System.out.println("retrieve user");
		
		return new UserDetails() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() {

				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {

				return true;
			}

			@Override
			public boolean isAccountNonLocked() {

				return true;
			}

			@Override
			public boolean isAccountNonExpired() {

				return true;
			}

			@Override
			public String getUsername() {

				return "pino";
			}

			@Override
			public String getPassword() {

				return "pino";
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {

				return null;
			}
		};
	}

}