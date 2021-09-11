package com.khoepv;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.khoepv.entity.Account;
import com.khoepv.service.AccountService;
import com.khoepv.service.AuthorityService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	AccountService accountService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	BCryptPasswordEncoder pe;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(idaccounts ->{
			try {
				Account acc = accountService.findById(idaccounts);
				String password = pe.encode(acc.getPassword());
				String[] role = authorityService.findRoleByUsername(idaccounts).stream()
						.map(au -> au.getIdrole())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(idaccounts).password(password).roles(role).build();
			} catch (NoSuchElementException e) {
				throw new NoSuchElementException(idaccounts +"not found!");
			}
		});
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF, CORS
		http.csrf().disable();

		//Phân quyền demo theo role
		http.authorizeRequests()
		.antMatchers("/admin/**").hasAnyRole("PM","ADMIN")
		.antMatchers("/rest/authorities/**").hasRole("ADMIN")
		.antMatchers("/order/**").hasAnyRole("PM","ADMIN","USER")
		.anyRequest().permitAll();
		
		//Điều khiển lỗi truy cập không theo đúng vai trò
		http.exceptionHandling()
		.accessDeniedPage("/security/unauthoried");
		
		http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
				.defaultSuccessUrl("/security/login/success", false).failureUrl("/security/login/error")
				.usernameParameter("username").passwordParameter("password");
			
		http.rememberMe().tokenValiditySeconds(86400);

		
		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success").addLogoutHandler(new SecurityContextLogoutHandler());
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Truy xuất REST API từ bên ngoài (domian khác)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}
}
