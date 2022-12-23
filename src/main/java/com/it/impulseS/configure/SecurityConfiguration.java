package com.it.impulseS.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.it.impulseS.configure.jwt.AuthorizationFilter;
import com.it.impulseS.repository.UserRepository;
import com.owl.env.config.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class SecurityConfiguration {

	@Autowired
	private UserRepository userRepo;
	
	

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.cors().and().csrf().disable()
	 * .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
	 * and() .authorizeRequests().antMatchers("/apikey/getApiKey/**").permitAll()
	 * .antMatchers("/apikey/register/**").permitAll()
	 * .antMatchers("/apikey/addNewAccount/**").permitAll()
	 * .antMatchers("/apikey/login/**").permitAll() .anyRequest().authenticated();
	 * http.addFilterBefore(getAuthenticationJwtTokenFilter(),
	 * UsernamePasswordAuthenticationFilter.class); }
	 */

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new AuthorizationFilter(
						authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), this.userRepo),
						UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers("/apikey/getApiKey/**").permitAll().antMatchers("/apikey/register/**")
				.permitAll().antMatchers("/apikey/addNewAccount/**").permitAll().antMatchers("/apikey/login/**")
				.permitAll().antMatchers("/apikey/getUserDetail/**").permitAll()
				.antMatchers("/apikey/ctrlEmail/**").permitAll()
				.antMatchers("/apikey/ctrlPhone/**").permitAll()
				//.antMatchers("/apikey/getContactsDetail/**").permitAll()
				.antMatchers("/impulse-websocket/**").permitAll()
				.anyRequest().authenticated();
		return http.build();

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	RequestRejectedHandler requestRejectedHandler() {
		return new HttpStatusRequestRejectedHandler();
	}
}
