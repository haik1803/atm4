package com.yoga.atm.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoga.atm.app.dao.AccountRepository;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repository;

	@Override
	public Account findByAccountNumber(String accountNumber) {
		try {
			return repository.findById(accountNumber).get();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Account save(Account account) {
		return repository.save(account);
	}

	@Override
	public List<Account> findAll() {
		return (List<Account>) repository.findAll();
	}

}
