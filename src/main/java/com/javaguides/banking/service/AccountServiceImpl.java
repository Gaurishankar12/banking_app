package com.javaguides.banking.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.javaguides.banking.dto.AccountDto;
import com.javaguides.banking.entity.Account;
import com.javaguides.banking.mapper.AccountMapper;
import com.javaguides.banking.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accRepo;


	public AccountServiceImpl(AccountRepository accRepo) {
		super();
		this.accRepo = accRepo;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account = AccountMapper.mapToAccount(accountDto);
		AccountDto saveAccount = AccountMapper.mapToAccountDto(accRepo.save(account));
		return saveAccount;
	}

	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accRepo.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposite(Long id, Double amount) {
		// TODO Auto-generated method stub
		Account account = accRepo.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
		double total = account.getBalance()+amount;
		account.setBalance(total);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto withdraw(Long id, Double amount) {
		// TODO Auto-generated method stub
		Account account = accRepo.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
		
		if(amount>account.getBalance()) {
			throw new RuntimeException("Insufficient balance");
		}
		
		double total = account.getBalance()-amount;
		account.setBalance(total);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Account account = accRepo.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
		accRepo.deleteById(id);
	}

}
