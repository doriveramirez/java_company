package com.doriv.api_company.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/", "/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.csrf().disable().formLogin()
		.loginPage("/login")
		.failureUrl("/login_error")
		.defaultSuccessUrl("/")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/access_denied");
		
		//		http.authorizeRequests()
//			.antMatchers("/", "/home", "/index", "/register", "/items", "/api/**", "/users").permitAll()
//			.antMatchers("/", "/home", "/index").hasAnyRole("USER","ADMIN")
//			.antMatchers("/api/**", "/users").hasRole("ADMIN")
//			.anyRequest().authenticated()
//			.and()
//			.formLogin()
//			.and()
//			.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//			.logoutSuccessUrl("/")
//			.and()
//			.exceptionHandling()
//			.accessDeniedPage("/access_denied");
//		http.authorizeRequests()
//		.antMatchers("/login", "/register").permitAll()
//		.antMatchers("/", "/home", "/index").hasRole("USER")
//		.antMatchers("/api/**").hasAnyAuthority("ADMIN")
//		.anyRequest().authenticated()
//		.and()
//		.csrf().disable().formLogin()
//		.loginPage("/login")
//		.failureUrl("/login_error")
//		.defaultSuccessUrl("/")
//		.and()
//		.logout()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/")
//		.and()
//		.exceptionHandling()
//		.accessDeniedPage("/access_denied");
	}
	
}
