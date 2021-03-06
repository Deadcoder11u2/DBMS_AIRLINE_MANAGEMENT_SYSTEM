package com.example.demo.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    public WebSecurity(CustomerService appUserService) {
	this.appUserService = appUserService;
    }

    private final CustomerService appUserService;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().antMatchers("/user/register").permitAll().and().authorizeRequests()
		.antMatchers("/user/book/**").hasAnyAuthority("USER", "ADMIN").and().authorizeRequests()
		.antMatchers("/admin/home").hasAnyAuthority("ADMIN").and().formLogin().loginPage("/login").permitAll()
		.and().logout().clearAuthentication(true).logoutSuccessUrl("/").deleteCookies("JSESSIONID")
		.invalidateHttpSession(true).and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	provider.setPasswordEncoder(getPasswordEncoder());
	provider.setUserDetailsService(appUserService);
	return provider;
    }
}
