package com.jwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	MyUserDetailsService userDetail;
//	@Autowired
//	JwtFilter jwtFilter;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetail);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
		
 
	}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
				http.csrf(csrf->csrf.disable());
				http.authorizeHttpRequests(auth->auth.requestMatchers("register","login")
						.permitAll()
						.anyRequest().authenticated());
				//http.formLogin(Customizer.withDefaults());
				http.httpBasic(Customizer.withDefaults());
			http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			//.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
			return http.build();
	}
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	return config.getAuthenticationManager();
    }
  //  @SuppressWarnings("deprecation")
//	@Bean
//    public UserDetailsService useDetailsService() {
//    	
//		UserDetails user = User.withDefaultPasswordEncoder()
//				   .username("ritu")
//    			  .password("12345")
//    			   .roles("USER")
//    			   .build();
//    	
//		UserDetails admin = User.withDefaultPasswordEncoder()
//				.username("admin")
//   			  .password("password")
//   			   .roles("ADMIN")
//   			   .build();
//		return new InMemoryUserDetailsManager(user,admin);
//    	
//    }

	

}
