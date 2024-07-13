package com.nagarro.customermgmtservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ACC-MGMT-SERVICE")
public interface AccountInterface {

	@PostMapping("account/create")
	ResponseEntity<String> createAcc(@RequestBody String username);
	
	@PostMapping("account/deleteAcc")
	ResponseEntity<String> deleteAccFeign(@RequestBody String username);

}
