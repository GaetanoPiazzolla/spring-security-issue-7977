package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.example.demo.CustomUserDetailService;
import com.example.demo.authproviders.RestAuthenticationProvider;
import com.example.demo.jwt.JWTConfigurer;
import com.example.demo.jwt.JwtAuthenticationEntryPoint;
import com.example.demo.jwt.TokenProvider;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationProvider authenticationProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;
    
    @Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private TokenProvider tokenProvider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	//FIXME: try and change this to the other CUSTOM USER AUTH PROVIDER. None of them will be used.
        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(customUserDetailService);
        super.configure(auth);
    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()

		// don't authenticate this particular requests
		.authorizeRequests()
		.antMatchers("something")
		.permitAll().

		// all other requests need to be authenticated
		anyRequest().authenticated().and()

		.apply(securityConfigurerAdapter()).and().

		// make sure we use stateless session; session won't be used to
		// store user's state.
		exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
    
	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}
    
}