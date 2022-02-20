package com.rdsic.back_end_e_tool.repository;

import com.rdsic.back_end_e_tool.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByUsernameAndPasswordAndDeletedFalse(String username, String pass);
}