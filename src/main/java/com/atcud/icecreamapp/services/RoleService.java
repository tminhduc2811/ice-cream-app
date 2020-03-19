package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Role;

public interface RoleService {
	
	public List<Role> getAllRoles();
	
	public Optional<Role> getRoleById(Long id);
	
	public Role save(Role role);
	
	public void delete(Role role);
	
	public void update(Role role);
	
}
