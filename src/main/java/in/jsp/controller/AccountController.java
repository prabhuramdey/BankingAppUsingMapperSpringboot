package in.jsp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.jsp.dto.AccountDto;
import in.jsp.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	
	//ADD ACCOUNT REST API
	@PostMapping("/account")
	public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto){
		AccountDto createAccount = accountService.createAccount(accountDto);
		return new ResponseEntity<>(createAccount, HttpStatus.CREATED);
	}
	
	
	//GET ACCOUNT REST API
	@GetMapping("/account/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Integer id){
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	
	//DEPOSITE REST API
	@PutMapping("/account/{id}/deposite")
	public ResponseEntity<AccountDto> deposite(@PathVariable Integer id,
						     @RequestBody Map<String, Double>request){
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.deposite(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	
	//WITHDRAW REST API
	@PutMapping("/account/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Integer id,
						     @RequestBody Map<String, Double>request){
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	
	//GET ALL ACCOUNT REST API
	@GetMapping("/account")
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		return ResponseEntity.ok(allAccounts);
	}
	
	
	//DELETE ACCOUNT REST API
	@DeleteMapping("/account/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Integer id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted Sucessfully !!");
	}
	
}
