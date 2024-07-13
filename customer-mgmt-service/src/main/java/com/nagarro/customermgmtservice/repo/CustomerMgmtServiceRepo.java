package com.nagarro.customermgmtservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.customermgmtservice.model.User;

@Repository
public interface CustomerMgmtServiceRepo extends JpaRepository<User, String>{

}
