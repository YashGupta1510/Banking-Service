package com.nagarro.customermgmtservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.nagarro.customermgmtservice.feign.AccountInterface;

@SpringBootApplication
@EnableFeignClients(clients = {AccountInterface.class})
public class CustomerMgmtServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMgmtServiceApplication.class, args);
	}

}
