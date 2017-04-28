package com.xcompany.xproject.common.web.starter.aaa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .csrf().disable() // oauth server no need csrf
	        .httpBasic().disable()
	        .authorizeRequests()
		        .antMatchers("/", "/login**").permitAll()
		        .anyRequest().authenticated() //other need authenticated
		        .and()
		    .formLogin()
		    	.loginPage("/login.html")
		    	.failureUrl("/login?error")
		    	.permitAll()
		    	.and()
	        .logout()
	        	.permitAll()
	        	.logoutSuccessUrl("/login.html")
	        	.and()
	        .exceptionHandling()
	        	.accessDeniedPage("/login?error");
	        	//.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.jdbcAuthentication()
//    		.dataSource(dataSource)
//    		.
        auth.inMemoryAuthentication()
                .withUser("user").password("123456").roles("USERS")
                .and()
                .withUser("admin").password("123456").roles("ADMIN", "ACTUATOR");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // AuthenticationManager bean , userd by oauth server
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // UserDetailService bean , userd by oauth server
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

}

