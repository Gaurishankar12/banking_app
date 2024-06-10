package com.javaguides.banking.service;

import com.javaguides.banking.dto.AccountDto;

public interface AccountService {

	public AccountDto createAccount(AccountDto accountDto);
	
	public AccountDto getAccountById(Long id);
	
	public AccountDto deposite(Long id, Double amount);
	
	public AccountDto withdraw(Long id, Double amount);
	
	public void delete(Long id);
}
