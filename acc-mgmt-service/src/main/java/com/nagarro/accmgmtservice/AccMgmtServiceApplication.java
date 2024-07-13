package com.nagarro.accmgmtservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.nagarro.accmgmtservice.feign.CustomerInterface;

@SpringBootApplication
@EnableFeignClients(clients = {CustomerInterface.class})
public class AccMgmtServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccMgmtServiceApplication.class, args);
	}

}
