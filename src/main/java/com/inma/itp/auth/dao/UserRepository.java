package com.inma.itp.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inma.itp.config.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
