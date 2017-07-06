package com.tustaml.config.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	 private static final String RESOURCE_ID = "rest_api";
     
	 @Autowired
	 private TokenStore tokenStore;
	 
		 @Autowired
		 JwtAccessTokenConverter accessTokenConverter;
		 
	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) {
	        resources.tokenStore(tokenStore).resourceId(RESOURCE_ID);
	    }
	 
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	    	http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
            .and()
            .requestMatchers()
            .antMatchers("/**")
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
            .antMatchers(HttpMethod.PATCH, "/api/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.POST, "/api/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.PUT, "/api/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.DELETE, "/api/**").access("#oauth2.hasScope('write')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
	    }
}
