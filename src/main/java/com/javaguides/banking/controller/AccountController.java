package com.javaguides.banking.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.banking.dto.AccountDto;
import com.javaguides.banking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accServ;

	public AccountController(AccountService accServ) {
		super();
		this.accServ = accServ;
	}
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accServ.createAccount(accountDto),HttpStatus.CREATED);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
		return new ResponseEntity<>(accServ.getAccountById(id),HttpStatus.OK);
	}
	
	@PutMapping("/{id}/deposite")
	public ResponseEntity<AccountDto> deposite(@PathVariable Long id,@RequestBody Map<String,Double> request){
		double amount = request.get("amount");
		AccountDto accountDto = accServ.deposite(id, amount);
		return new ResponseEntity<>(accountDto,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
		double amount = request.get("amount");
		return new ResponseEntity<>(accServ.withdraw(id, amount),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> delete(@PathVariable Long id){
		accServ.delete(id);
		return new ResponseEntity<>("Account deleted" , HttpStatus.OK);
	}
}
