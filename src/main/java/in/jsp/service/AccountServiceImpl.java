package in.jsp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jsp.dto.AccountDto;
import in.jsp.entity.Account;
import in.jsp.mapper.AccountMapper;
import in.jsp.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);
		Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}

	
	@Override
	public AccountDto getAccountById(Integer id) {
		Account account=accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Doesnot Exist"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposite(Integer id, double amount) {
		Account account=accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Doesnot Exist"));
		
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account saveAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(saveAccount);
	}


	@Override
	public AccountDto withdraw(Integer id, double amount) {
		Account account=accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Doesnot Exist"));
		
		if(account.getBalance()< amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account saveAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(saveAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	}


	@Override
	public void deleteAccount(Integer id) {
		Account account=accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account Doesnot Exist"));
		
		accountRepository.deleteById(id); 	
	}
	
}
