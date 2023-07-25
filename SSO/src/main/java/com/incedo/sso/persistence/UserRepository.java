package com.incedo.sso.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.sso.models.User;


public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
}
