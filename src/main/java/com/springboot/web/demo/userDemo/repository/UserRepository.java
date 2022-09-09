package com.springboot.web.demo.userDemo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.web.demo.userDemo.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	void deleteByUsername(String username);
	
	@Modifying
	@Query(" update User u set u.username = ?1, u.password = ?2, u.name = ?3 where u.id = ?4")
	void updateUserById(String username, String password, String name, Integer userid);
}
