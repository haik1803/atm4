package com.yoga.atm.app.service;

import com.yoga.atm.app.Exception.WrongInputException;
import com.yoga.atm.app.model.Account;

public interface ValidationService {

	public void validateAccountNumber(String accountNumber) throws WrongInputException;

	public void validatePin(String pin) throws WrongInputException;

	public Account validateAccount(String accountNumber, String pin);

	public void validateAmountTransfer(String amount, Double accountBalance) throws WrongInputException;

	public void validateAmountWithdraw(String amount, Double accountBalance) throws WrongInputException;
}
