/*package com.example.demo.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//security being used with jwt tokens
@EnableAuthorizationServer
@Configuration
public class JwtConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	MyUserDetailsService service;

	@Autowired
	AuthenticationManager authenticationManager;

	@Value("${oauth.tokenTimeout:3600}")
	private int expiration;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Value("${oauth2.signingkey:payal123}")
	private String signngKey;

	@Autowired
	private TokenStore tokenStore;
	@Autowired DataSource ds;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Bean
	public TokenStore store() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signngKey);
		return converter;
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain chain = new TokenEnhancerChain();
		chain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
		endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter)
		.tokenEnhancer(chain);
		endpoints.authenticationManager(authenticationManager);
		endpoints.userDetailsService(service);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("payal").secret("secret").accessTokenValiditySeconds(expiration)
		.scopes("read","write").authorizedGrantTypes("password","refresh_token")
		.resourceIds("resource");
		clients.jdbc(ds).passwordEncoder(encoder());
	}
	
}
*/