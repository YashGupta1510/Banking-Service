package com.nagarro.customermgmtservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nagarro.customermgmtservice.feign.AccountInterface;
import com.nagarro.customermgmtservice.model.Creds;
import com.nagarro.customermgmtservice.model.User;
import com.nagarro.customermgmtservice.model.UserCreationDTO;
import com.nagarro.customermgmtservice.model.UserDTO;
import com.nagarro.customermgmtservice.repo.CustomerMgmtServiceRepo;

@Service
public class CustomerMgmtService {

	@Autowired
	private CustomerMgmtServiceRepo repo;
	
	@Autowired
	private AccountInterface ain;
	
	public ResponseEntity<String> addUser(UserCreationDTO usercDTO) {
		User user = new User();
		user.setName(usercDTO.getName());
		user.setPin(usercDTO.getPin());
		user.setUsername(usercDTO.getUsername());
		user.setAge(usercDTO.getAge());
		ain.createAcc(usercDTO.getUsername());
		repo.save(user);
		return new ResponseEntity<String>("Created Success", HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteUserFn(String username) {
		repo.deleteById(username);
		return new ResponseEntity<String>("Deleted Success", HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteUser(String username) {
		repo.deleteById(username);
		ain.deleteAccFeign(username);
		return new ResponseEntity<String>("Deleted Success", HttpStatus.OK);
	}
	
	
	public ResponseEntity<String> updateUser(User user) {
		repo.save(user);
		return new ResponseEntity<String>("Saved Success", HttpStatus.OK);
	}
	
	public ResponseEntity<List<UserDTO>> allUsers(){
		
		List<User> list = (List<User>) repo.findAll();
		List<UserDTO> l = new ArrayList<UserDTO>();
		list.forEach((e) -> {
			UserDTO u = new UserDTO();
			u.setAge(e.getAge());
			u.setName(e.getName());
			u.setUsername(e.getUsername());
			l.add(u);
		});
		
		return new ResponseEntity<List<UserDTO>>(l, HttpStatus.OK);
	}
	
	public ResponseEntity<UserDTO> getUsers(String username){
		User user = repo.findById(username).get();
		UserDTO u = new UserDTO();
		u.setAge(user.getAge());
		u.setName(user.getName());
		u.setUsername(user.getUsername());
		
		return new ResponseEntity<UserDTO>(u, HttpStatus.OK);
	}
	
	
	public Boolean verify(Creds creds){
		String username = creds.getUsername();
		int pin = creds.getPin();
		
		int pinO = repo.findById(username).get().getPin();
		
		if(pin == pinO){
			return true;
		}else {
			return false;
		}
	}
}
