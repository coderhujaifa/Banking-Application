package com.BankingAPPSpringBoot.BankingApplication.Service;

import java.util.List;

import com.BankingAPPSpringBoot.BankingApplication.Dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto account);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id,double amount);
	
	AccountDto withdraw(Long id,double amount);
	
	List<AccountDto> getAllAcount ();
	
	void deleteAccount(Long id);
}