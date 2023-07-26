package com.incedo.sso.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.incedo.sso.models.Logs;
import com.incedo.sso.models.User;
import com.incedo.sso.persistence.LogsRepository;
import com.incedo.sso.persistence.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	public UserRepository userRespository;
	
	@Autowired
	public LogsRepository logsRepository;
	
	@Autowired
	public PasswordEncoder encoder;
	
	public User insert(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole("USER");
		return userRespository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRespository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		Collection<GrantedAuthority> sga = new ArrayList<>();
		sga.add(new SimpleGrantedAuthority(user.getRole()));
		Logs log = new Logs();
		log.setName(user.getUsername());
		log.setLoggedInAt(LocalDate.now());
		logsRepository.save(log);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), sga);
	}

	public User getUser(String username) {
		return userRespository.findByUsername(username);
	}

	public List<User> getAll() {
		return userRespository.findAll();
	}
	
	

}
