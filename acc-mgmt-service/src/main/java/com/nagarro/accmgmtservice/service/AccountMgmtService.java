package com.nagarro.accmgmtservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nagarro.accmgmtservice.feign.CustomerInterface;
import com.nagarro.accmgmtservice.model.Account;
import com.nagarro.accmgmtservice.model.AccountDTO;
import com.nagarro.accmgmtservice.model.Creds;
import com.nagarro.accmgmtservice.model.Txn;
import com.nagarro.accmgmtservice.model.UserDTO;
import com.nagarro.accmgmtservice.repo.AccountMgmtServiceRepo;

@Service
public class AccountMgmtService {

	@Autowired
	private AccountMgmtServiceRepo repo;

	@Autowired
	private CustomerInterface cin;
	
	public ResponseEntity<String> createAcc(String username) {
		Account acc = new Account();
		acc.setAmount(0.0);
		acc.setUsername(username);
		repo.save(acc);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteAccFn(String username) {
		repo.deleteById(username);
		return new ResponseEntity<String>("Delete Success", HttpStatus.OK);
	}
	
	public Boolean verify(Creds creds){
		return cin.verify(creds);
	}

	public ResponseEntity<String> transaction(Txn txn) {
		if(verify(txn.getCreds())) {
			return transact(txn.getCreds().getUsername(), txn.getAction(), txn.getAmount());
		}else {
			return new ResponseEntity<String>("Invalid Credentials", HttpStatus.OK);
		}
	}

	public ResponseEntity<String> transact(String username, String action, double amount) {
		
		Account acc = repo.findById(username).get();
		double amt = acc.getAmount();
		
		if(action.equalsIgnoreCase("withdraw")) {
			if(amt < amount) {
				return new ResponseEntity<String>("Insufficient amount", HttpStatus.OK);
			}else {
				amt = amt - amount;
				acc.setAmount(amt);
				repo.save(acc);
				return new ResponseEntity<String>("Amount withdrawn, Success. Available Balance = "+ amt,HttpStatus.OK);
			}
		}else {
			amt = amt + amount;
			acc.setAmount(amt);
			repo.save(acc);
			return new ResponseEntity<String>("Amount Deposited, Success. Available Balance = "+ amt, HttpStatus.OK);
		}
	}
	
	public ResponseEntity getDetails(Creds creds){
		if(verify(creds)) {
			String username = creds.getUsername();
			Account acc = repo.findById(username).get();
			
			AccountDTO adto = new AccountDTO();
			adto.setUsername(username);
			adto.setAmount(acc.getAmount());
			
			UserDTO userDTO = cin.getUser(username).getBody();
			
			adto.setAge(userDTO.getAge());
			adto.setName(userDTO.getName());
			
			return new ResponseEntity<AccountDTO>(adto, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("Invalid Credentials", HttpStatus.OK);
		}
	}

	public ResponseEntity getAccount(Creds creds) {
		System.out.println("---------------acc service------"+creds.toString()+"--------------");
		if(verify(creds)) {
			String username = creds.getUsername();
			Account acc = repo.findById(username).get();
			return new ResponseEntity<Account>(acc, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("INVALID CREDS", HttpStatus.OK);
		}
	}

	public ResponseEntity<String> deleteAcc(String username) {
		repo.deleteById(username);
		cin.deleteUserFeign(username);
		return new ResponseEntity<String>("Delete Success", HttpStatus.OK);
	}
	
	
	
}
