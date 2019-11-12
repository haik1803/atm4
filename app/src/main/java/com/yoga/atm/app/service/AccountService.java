package com.yoga.atm.app.service;

import java.util.List;

import com.yoga.atm.app.model.Account;

public interface AccountService {
	public Account findByAccountNumber(String accountNumber);

	public Account save(Account account);

	public List<Account> findAll();
}
