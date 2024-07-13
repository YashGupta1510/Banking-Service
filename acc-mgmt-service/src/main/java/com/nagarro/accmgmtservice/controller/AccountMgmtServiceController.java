package com.nagarro.accmgmtservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.accmgmtservice.model.Creds;
import com.nagarro.accmgmtservice.model.Txn;
import com.nagarro.accmgmtservice.service.AccountMgmtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("account")
public class AccountMgmtServiceController {

	@Autowired
	private AccountMgmtService service;
	
	@PostMapping(value = "create")
	ResponseEntity<String> createAcc(@RequestBody String username){
		return service.createAcc(username);
	}
	
	@PostMapping(value = "delete")
	ResponseEntity<String> deleteAcc(@RequestBody String username){
		return service.deleteAcc(username);
	}
	
	@PostMapping(value = "deleteAcc")
	ResponseEntity<String> deleteAccFeign(@RequestBody String username){
		return service.deleteAccFn(username);
	}
	
	@PostMapping(value = "transaction")
	ResponseEntity<String> transaction(@Valid @RequestBody Txn txn){
		return service.transaction(txn);
	}
	
	@PostMapping(value = "getAccount")
	ResponseEntity getAccount(@RequestBody Creds creds){
		return service.getAccount(creds);
	}

	@PostMapping(value = "getDetails")
	ResponseEntity getDetails(@RequestBody Creds creds){
		return service.getDetails(creds);
	}
}
