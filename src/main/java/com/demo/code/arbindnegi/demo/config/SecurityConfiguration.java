/**
 * 
 */
package com.demo.code.arbindnegi.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.demo.code.arbindnegi.demo.security.CustomUserDetailService;
import com.demo.code.arbindnegi.demo.security.JwtAuthenticationEntryPoint;
import com.demo.code.arbindnegi.demo.security.JwtAuthenticationFilter;

/**
 * @author Arbind Negi 15-May-2022
 *
 */

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    public static final String[] PUBLIC_ACCESSABLE_URL= {
	    "/api/v1/auth/**",
	    "/v3/api-docs",
	    "/v2/api-docs",
	    "/swagger-resources/**",
	    "/swagger-ui/**",
	    "/webjars/**"
    };
    
   @Autowired
    private CustomUserDetailService customUserDetailService;
   
   @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	
	http.csrf().disable()
	// dont authenticate this particular request
	.authorizeRequests()
	.antMatchers(PUBLIC_ACCESSABLE_URL).permitAll()
	.antMatchers(HttpMethod.GET).permitAll()
	// all other requests need to be authenticated
	.anyRequest()
	.authenticated()
	.and()
	/* make sure we use stateless session; session won't be used to
	   store user's state. 
	 */
	.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
	.and()
	.sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/api/v1/auth/login");
    }
    
   /*@Override
    protected void configure(HttpSecurity http) throws Exception {
	
	http.csrf().disable()
	.authorizeHttpRequests()
	.anyRequest()
	.authenticated()
	.and()
	.httpBasic();
	
    } */
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(this.customUserDetailService)
	.passwordEncoder(passwordEncoder());
    }

   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 configure AuthenticationManager so that it knows from where to load
		   user for matching credentials
		   Use BCryptPasswordEncoder 
		
		auth.userDetailsService(this.customUserDetailService)
		.passwordEncoder(passwordEncoder());
	}*/
    
    @Bean
    public PasswordEncoder passwordEncoder() {
	
	return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }
    
}
