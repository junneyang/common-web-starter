package com.xcompany.xproject.common.web.starter.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        		//.antMatchers("/api/xproject-common-web-starter-aaa/user**").permitAll() // /api/**")
        		//.anyRequest().authenticated()
        		.anyRequest().permitAll()
        		.and()
        	.sessionManagement()
        		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }
    
    @Primary
    @Bean
    public ResourceServerTokenServices tokenService() {
       RemoteTokenServices tokenServices = new RemoteTokenServices();
       tokenServices.setClientId("acme");
       tokenServices.setClientSecret("acmesecret");
       tokenServices.setCheckTokenEndpointUrl("http://10.67.13.168:8001/api/xproject-common-web-starter-aaa/oauth/check_token");
       return tokenServices;
    }
    
}

