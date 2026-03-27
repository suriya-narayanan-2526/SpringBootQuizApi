package finalDemoApplication.demo.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import finalDemoApplication.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtFilter jwtfilter;
	@Bean
	public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception
	{
		http.csrf(csrf->csrf.disable())
		.sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth->
		auth.requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
		.requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
		.anyRequest().authenticated()
		)
		.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsservice()
	{
		return customUserDetailsService;
	}
	@Bean
	public DaoAuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsservice());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authManager()
	{
		return new ProviderManager(List.of(authProvider()));
	}
	@Bean
    public FilterRegistrationBean<JwtFilter> filterRegistrationBean(JwtFilter jwtfilter)
    {
    	FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>(jwtfilter);
    	registration.setEnabled(false);
    	return registration;
    }


}
