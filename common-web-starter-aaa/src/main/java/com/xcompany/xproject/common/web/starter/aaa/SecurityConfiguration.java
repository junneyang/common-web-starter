package com.xcompany.xproject.common.web.starter.aaa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
	        .csrf().disable() // oauth server no need csrf
	        .httpBasic().disable()
	        .authorizeRequests()
	        	.antMatchers("/login**").permitAll()
		        //.antMatchers("/", "/login**").permitAll()
		        .anyRequest().authenticated() //other need authenticated
	        	//.anyRequest().permitAll()
		        .and()
		    .formLogin()
		    	.loginPage("/login")
		    	.loginProcessingUrl("/login")
		    	.usernameParameter("username")
		    	.passwordParameter("password")
		    	.failureUrl("/login?error")
		    	.defaultSuccessUrl("/")
		    	.permitAll()
		    	.and()
	        .logout()
	        	.permitAll()
	        	.logoutSuccessUrl("/login")
	        	.and()
	        .exceptionHandling()
//	        	.accessDeniedHandler(new AccessDeniedHandlerImpl() {
//	        		@Override
//	        		public void handle(HttpServletRequest request,
//	        				HttpServletResponse response, AccessDeniedException accessDeniedException)
//	        				throws IOException, ServletException, PermissionException {
//	        			// TODO Auto-generated method stub
//	        			//super.handle(arg0, arg1, arg2);
//	        			throw new PermissionException(ResponseCode.PERMISSION_EXCEPTION, null, null);
//	        		}
//	        	})
	        	.accessDeniedPage("/login?error");
	        	//.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.jdbcAuthentication()
//    		.dataSource(dataSource)
//    		.
        auth.inMemoryAuthentication()
                .withUser("user").password("123456").roles("USER")
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
    
    @Configuration
    @EnableResourceServer
    @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
            	.authorizeRequests()
            		//.antMatchers("/api/xproject-common-web-starter-aaa/user**").permitAll() // /api/**")
            		.antMatchers("/login**").permitAll()
            		.anyRequest().authenticated()
            		//.anyRequest().permitAll()
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

}

