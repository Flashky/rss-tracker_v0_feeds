package com.flashk.apis.rsstracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests(authz -> authz
        	.antMatchers(HttpMethod.GET, "/feeds/**").hasAuthority("SCOPE_read_feed")
            .antMatchers(HttpMethod.POST, "/feeds").hasAuthority("SCOPE_write_feed")
            .anyRequest().authenticated())
          .oauth2ResourceServer(oauth2 -> oauth2.jwt());
	}
    
}
