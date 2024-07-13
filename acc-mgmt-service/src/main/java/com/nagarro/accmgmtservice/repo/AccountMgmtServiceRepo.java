package com.nagarro.accmgmtservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.accmgmtservice.model.Account;

@Repository
public interface AccountMgmtServiceRepo extends JpaRepository<Account, String>{

	
}
