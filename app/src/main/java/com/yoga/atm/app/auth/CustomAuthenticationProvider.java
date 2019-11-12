package com.yoga.atm.app.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.yoga.atm.app.Exception.CustomAuthenticationException;
import com.yoga.atm.app.Exception.WrongInputException;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.service.ValidationService;

@Component
@PropertySource("classpath:message.properties")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private ValidationService validationService;

	@Value("${app.unknown.error}")
	String somethingWrongMessage;

	@Value("${app.invalid.account}")
	String invalidAccountMessage;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String accountNumber = authentication.getName();
		String pin = authentication.getCredentials().toString();
		try {
			validationService.validateAccountNumber(accountNumber);
			validationService.validatePin(pin);
			Account account = validationService.validateAccount(accountNumber, pin);
			if (account != null)
				return new UsernamePasswordAuthenticationToken(account, null, new ArrayList<>());
		} catch (WrongInputException e) {
			throw new CustomAuthenticationException(e.getMessage());
		} catch (Exception e) {
			throw new CustomAuthenticationException(somethingWrongMessage);
		}
		throw new CustomAuthenticationException(invalidAccountMessage);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}