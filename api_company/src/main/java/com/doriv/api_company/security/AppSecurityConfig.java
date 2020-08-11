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
			.antMatchers("/", "/home", "/index", "/login", "/register").permitAll()
			.antMatchers("/api/**").hasAnyAuthority("SUPER_USER","ADMIN_USER","SITE_USER")
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
//		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().and()
//		.formLogin().loginPage("/login").permitAll().and()
//		.logout().invalidateHttpSession(true).clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/logout_success").permitAll();
	}
	
}
