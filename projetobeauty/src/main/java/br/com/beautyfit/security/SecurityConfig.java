package br.com.beautyfit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.beautyfit.endpoint.Path;
import br.com.beautyfit.services.UserDetailsFitService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsFitService userDetailsFitService;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsFitService);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	http.httpBasic().and()
    		.exceptionHandling().and()
    		.anonymous().and()
    		.servletApi().and()
    		.headers().cacheControl().and()
    		.authorizeRequests()
    		
    		.antMatchers("/").permitAll()
    		.antMatchers(Path.urls.CLIENTS + "/**").hasRole("ADMIN")
    		.anyRequest().authenticated().and();
    	
    	http.csrf().disable();
    }

}
