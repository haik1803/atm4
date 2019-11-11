package com.yoga.atm.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yoga.atm.app.auth.CustomAuthenticationFailureHandler;
import com.yoga.atm.app.auth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/pin").permitAll().antMatchers("/upload")
				.permitAll().antMatchers("/transaction").authenticated().antMatchers("/getDataTransaction")
				.authenticated().antMatchers("/transferDestination").authenticated().antMatchers("/transferAmount")
				.authenticated().antMatchers("/transferConfirm").authenticated().antMatchers("/transfer")
				.authenticated().antMatchers("/transferSummary").authenticated().antMatchers("/viewTransaction")
				.authenticated().antMatchers("/withdraw").authenticated().antMatchers("/withdrawl").authenticated()
				.antMatchers("/withdrawSummary").authenticated().and().csrf().disable().formLogin().loginPage("/login")
				.failureHandler(customAuthenticationFailureHandler()).defaultSuccessUrl("/transaction")
				.usernameParameter("accountNumber").passwordParameter("pin").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
				.exceptionHandling().accessDeniedPage("/");
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/plugins/**","/fonts/**");
	}

	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}
}
