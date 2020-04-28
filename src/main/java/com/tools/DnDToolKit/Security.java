package com.tools.DnDToolKit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/app", "/battle", "/inventory", "/initiative").authenticated()
				.antMatchers( "/public/**").permitAll()
				.and()
			.formLogin()
				.defaultSuccessUrl("/app", true)
				.permitAll()
				.and()
			.logout()
				.permitAll();
		
		
		
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		
		UserDetails user2 =
				 User.withDefaultPasswordEncoder()
					.username("user2")
					.password("password2")
					.roles("USER")
					.build();
		UserDetails [] usss = {user, user2};
		return new InMemoryUserDetailsManager(usss);
	}
	
	
	
	
	
}
