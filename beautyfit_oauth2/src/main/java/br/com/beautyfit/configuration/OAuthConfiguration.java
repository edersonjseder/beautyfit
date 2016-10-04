package br.com.beautyfit.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import br.com.beautyfit.services.UserDetailsFitService;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter{
	
	private static final String RESOURCE_ID = "restservice";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsFitService userDetailsFitService;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource oauthDataSource(){
		return DataSourceBuilder.create().build();
	}
	
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(oauthDataSource());
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(oauthDataSource());
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
          		 .authenticationManager(authenticationManager)
        		 .userDetailsService(userDetailsFitService);
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient("projetobeautyfit") // Header parameter Base 64 encoded: Client:Secret = projetobeautyfit:123456
               .authorizedGrantTypes("password","authorization_code", "refresh_token", "client_credentials")
               .authorities("ADMIN")
               .scopes("read", "write")
               .resourceIds(RESOURCE_ID)
               .secret("123456");
        
        clients.configure(new ClientDetailsServiceBuilder<>());
        
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        
    	oauthServer.tokenKeyAccess("isAnonymous() || permitAll()").checkTokenAccess("permitAll()");
    	
    }

}
