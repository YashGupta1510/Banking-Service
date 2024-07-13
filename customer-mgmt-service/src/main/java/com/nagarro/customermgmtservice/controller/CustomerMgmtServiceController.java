package com.nagarro.customermgmtservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.customermgmtservice.model.Creds;
import com.nagarro.customermgmtservice.model.User;
import com.nagarro.customermgmtservice.model.UserCreationDTO;
import com.nagarro.customermgmtservice.model.UserDTO;
import com.nagarro.customermgmtservice.service.CustomerMgmtService;

@RestController
@RequestMapping("customer")
public class CustomerMgmtServiceController {

	@Autowired
	CustomerMgmtService service;
	
	@PostMapping("addUser")
	public ResponseEntity<String> addUser(@RequestBody UserCreationDTO usercDTO) {return service.addUser(usercDTO);}
	
	@DeleteMapping("delete/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username) {return service.deleteUser(username);}
	
	@DeleteMapping("deleteFn/{username}")
	public ResponseEntity<String> deleteUserFeign(@PathVariable String username) {return service.deleteUserFn(username);}
	
	@PostMapping("updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user) {return service.updateUser(user);}
	
	@GetMapping("allUsers")
	public ResponseEntity<List<UserDTO>> allUser() {return service.allUsers();}
	
	@GetMapping("getUser/{username}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String username) {return service.getUsers(username);}

	@PostMapping(value = "verify")
	public Boolean verify(@RequestBody Creds creds){ return service.verify(creds);
	}
}


