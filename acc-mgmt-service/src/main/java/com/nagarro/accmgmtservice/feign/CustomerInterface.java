package com.nagarro.accmgmtservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nagarro.accmgmtservice.model.Creds;
import com.nagarro.accmgmtservice.model.UserDTO;

@FeignClient("CUSTOMER-MGMT-SERVICE")
public interface CustomerInterface {

	@PostMapping(value = "customer/verify")
	public Boolean verify(@RequestBody Creds creds); 
	 
	@GetMapping("customer/getUser/{username}")
	ResponseEntity<UserDTO> getUser(@PathVariable String username);
	

	@GetMapping("customer/deleteFn/{username}")
	ResponseEntity<UserDTO> deleteUserFeign(@PathVariable String username);
	
}
