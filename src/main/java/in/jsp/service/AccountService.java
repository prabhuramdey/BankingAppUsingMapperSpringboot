package in.jsp.service;

import java.util.List;

import in.jsp.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Integer id);
	
	AccountDto deposite(Integer id, double amount);
	
	AccountDto withdraw(Integer id, double amount);
	
	List<AccountDto>getAllAccounts();
	
	void deleteAccount(Integer id);
}
