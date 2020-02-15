package com.example.demo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
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
