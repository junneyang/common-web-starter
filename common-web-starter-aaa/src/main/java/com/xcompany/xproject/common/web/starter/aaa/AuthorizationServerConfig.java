package com.xcompany.xproject.common.web.starter.aaa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
//@EnableResourceServer
//@Order(100-2)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
    UserDetailsService userDetailsService; // spring security UserDetailsService init by SecurityConfiguration
    @Autowired
    AuthenticationManager authenticationManager; // spring securityAuthenticationManager init by SecurityConfiguration.
    @Autowired
    private DataSource dataSource;
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //clients.inMemory()
    	clients.jdbc(dataSource)
                .withClient("acme") // default client id
                .secret("acmesecret")
                .authorizedGrantTypes("password", "implicit", "refresh_token", "authorization_code", "client_credentials")
                .scopes("read", "write")
                .autoApprove(true)
                //.autoApprove("read", "write")
                //.authorities("ROLE_USER")
                .accessTokenValiditySeconds(30);
                //.redirectUris("http://example.com");
        //clients.jdbc(dataSource);
        //.passwordEncoder(new );

    }
    
//    @Bean
//	public JwtAccessTokenConverter jwtAccessTokenConverter() {
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		KeyPair keyPair = new KeyStoreKeyFactory(
//				new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//				.getKeyPair("test");
//		converter.setKeyPair(keyPair);
//		return converter;
//	}
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
                //.accessTokenConverter(jwtAccessTokenConverter());
    }
    
    @Bean
    public TokenStore tokenStore(){
        //return new InMemoryTokenStore(); //in memory token store
    	return new JdbcTokenStore(dataSource);
    }
}
