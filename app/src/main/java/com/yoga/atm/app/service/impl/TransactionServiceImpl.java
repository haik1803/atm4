package com.yoga.atm.app.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.atm.app.Exception.TransactionFailureException;
import com.yoga.atm.app.dao.AccountRepository;
import com.yoga.atm.app.dao.TransactionRepository;
import com.yoga.atm.app.enumerable.TransactionType;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.model.Transaction;
import com.yoga.atm.app.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	@Transactional
	public Account withdraw(String accountNumber, double amount) throws TransactionFailureException {
		Account account = null;
		try {
			account = accountRepository.findByAccountNumber(accountNumber).get(0);
			account.setBalance(account.getBalance() - Double.valueOf(amount));
			Transaction transaction = new Transaction(TransactionType.WITHDRAW, account, Double.valueOf(amount),
					LocalDateTime.now(), null, null);
			account = accountRepository.save(account);
			transactionRepository.save(transaction);
		} catch (Exception e) {
			e.printStackTrace();
			account = null;
			throw new TransactionFailureException(e.getMessage());
		}
		return account;
	}

	@Override
	@Transactional
	public Account transfer(String accountNumber, double amount, String destinationAccountNumber, String reference)
			throws TransactionFailureException {
		Account account = null;
		try {
			account = accountRepository.findByAccountNumber(accountNumber).get(0);
			Account destAccount = accountRepository.findByAccountNumber(destinationAccountNumber).get(0);
			account.setBalance(account.getBalance() - Double.valueOf(amount));
			destAccount.setBalance(destAccount.getBalance() + Double.valueOf(amount));
			Transaction transaction = new Transaction(TransactionType.TRANSFER, account, Double.valueOf(amount),
					LocalDateTime.now(), destAccount, reference);
			account = accountRepository.save(account);
			accountRepository.save(destAccount);
			transactionRepository.save(transaction);
		} catch (Exception e) {
			e.printStackTrace();
			account = null;
			throw new TransactionFailureException(e.getMessage());
		}
		return account;
	}

	@Override
	public Long countByAccountNumber(String accountNumber) {
		return transactionRepository.countByAccountAccountNumber(accountNumber);
	}

	@Override
	public List<Transaction> findAllByAccountNumber(String accountNumber, Pageable pageable) {
		return transactionRepository.findAllByAccountAccountNumber(accountNumber, pageable);
	}

	@Override
	public void deleteAll() {
		transactionRepository.deleteAll();
	}

	@Override
	public Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
}
