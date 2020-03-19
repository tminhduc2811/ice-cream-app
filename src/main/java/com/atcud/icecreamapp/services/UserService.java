package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public Optional<User> getUserById(Long id);
	
	public User save(User user);
	
	public void delete(User user);
	
	public void update(User user);
	
}
