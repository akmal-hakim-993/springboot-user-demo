package com.springboot.web.demo.userDemo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.web.demo.userDemo.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	void deleteByUsername(String username);
	
}
