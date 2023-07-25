package com.incedo.notes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incedo.notes.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
}
