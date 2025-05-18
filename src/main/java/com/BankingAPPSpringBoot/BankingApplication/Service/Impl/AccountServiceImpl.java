package com.BankingAPPSpringBoot.BankingApplication.Service.Impl;

import org.springframework.stereotype.Service;

import com.BankingAPPSpringBoot.BankingApplication.Dto.AccountDto;
import com.BankingAPPSpringBoot.BankingApplication.Entity.Account;
import com.BankingAPPSpringBoot.BankingApplication.Mapper.AccountMapper;
import com.BankingAPPSpringBoot.BankingApplication.Repository.AccountRepository;
import com.BankingAPPSpringBoot.BankingApplication.Service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	
	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

}
