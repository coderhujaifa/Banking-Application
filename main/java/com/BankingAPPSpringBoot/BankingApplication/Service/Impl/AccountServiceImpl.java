package com.BankingAPPSpringBoot.BankingApplication.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		double totalbalance = account.getBalance() + amount;
		account.setBalance(totalbalance);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		double totalbalance = account.getBalance() - amount;
		account.setBalance(totalbalance);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAcount() {
		
		return accountRepository.findAll().stream().map((account) -> AccountMapper.mapToAccountDto(account)).
				collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		accountRepository.delete(account);
	}

}
