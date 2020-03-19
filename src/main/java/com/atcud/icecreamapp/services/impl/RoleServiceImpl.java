package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Role;
import com.atcud.icecreamapp.repositories.RoleRepository;
import com.atcud.icecreamapp.services.RoleService;

@Component
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> getRoleById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public void update(Role role) {
		roleRepository.update(role);
	}

}
