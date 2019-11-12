package com.yoga.atm.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.yoga.atm.app.Exception.WrongInputException;
import com.yoga.atm.app.dao.AccountRepository;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.service.ValidationService;

@Service
@PropertySource("classpath:message.properties")
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	AccountRepository repository;

	@Value("${app.invalid.account}")
	String invalidAccountMessage;

	@Value("${app.amount.number}")
	String invalidNumberFormatMessage;

	@Value("${app.amount.mintransfer}")
	String minimumTransferMessage;

	@Value("${app.amount.maxtransfer}")
	String maximumTransferMessage;

	@Value("${app.amount.insufficient}")
	String insuficientAmountMessage;

	@Value("${app.invalid.amount}")
	String invalidAmountMessage;

	@Value("${app.amount.maximum}")
	String maximumWithdrawMessage;
	
	@Value("${app.accountnumber.size}")
	String accountNumberLengthMessage;

	@Override
	public Account validateAccount(String accountNumber, String pin) {
		try {
			return repository.findByAccountNumberAndPin(accountNumber, pin).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void validateAccountNumber(String accountNumber) throws WrongInputException {
		if (!accountNumber.matches("[0-9]+")) {
			throw new WrongInputException(invalidAccountMessage);
		} else if (accountNumber.length() != 6) {
			throw new WrongInputException(accountNumberLengthMessage);
		}
		Account account = repository.findById(accountNumber).get();
		if (account == null) {
			throw new WrongInputException(invalidAccountMessage);
		}
	}

	@Override
	public void validateAmountTransfer(String amount, Double accountBalance) throws WrongInputException {
		if (!amount.matches("[0-9]+")) {
			throw new WrongInputException(invalidNumberFormatMessage);
		} else {
			if (Long.valueOf(amount) < 1) {
				throw new WrongInputException(minimumTransferMessage);
			} else if (Long.valueOf(amount) > 1000) {
				throw new WrongInputException(maximumTransferMessage);
			} else if (Long.valueOf(amount) > accountBalance) {
				throw new WrongInputException(insuficientAmountMessage + accountBalance);
			}
		}
	}

	@Override
	public void validateAmountWithdraw(String amount, Double accountBalance) throws WrongInputException {
		if (!amount.matches("[0-9]+")) {
			throw new WrongInputException(invalidNumberFormatMessage);
		} else {
			if (Double.valueOf(amount) % 10 != 0) {
				throw new WrongInputException(invalidAmountMessage);
			} else if (Double.valueOf(amount) > 1000) {
				throw new WrongInputException(maximumWithdrawMessage);
			} else if (Double.valueOf(amount) > accountBalance) {
				throw new WrongInputException(insuficientAmountMessage + accountBalance);
			}
		}
	}

	@Override
	public void validatePin(String pin) throws WrongInputException {
		if (!pin.matches("[0-9]+")) {
			throw new WrongInputException(invalidAccountMessage);
		} else if (pin.length() != 6) {
			throw new WrongInputException(accountNumberLengthMessage);
		}
	}
}
