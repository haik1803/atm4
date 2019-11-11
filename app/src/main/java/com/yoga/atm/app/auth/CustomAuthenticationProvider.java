package com.yoga.atm.app.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.service.AccountService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AccountService accountService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String accountNumber = authentication.getName();
		String pin = authentication.getCredentials().toString();

		if (accountNumber.length() != 6) {
			return null;
		} else if (!accountNumber.matches("[0-9]+")) {
			return null;
		} else if (pin.length() != 6) {
			return null;
		} else if (!pin.matches("[0-9]+")) {
			return null;
		}
		Account account = accountService.validate(accountNumber, pin);
		if (account == null) {
			return null;
		} else {
			return new UsernamePasswordAuthenticationToken(account, null, new ArrayList<>());
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}