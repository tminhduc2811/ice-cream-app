package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Role;

public interface RoleRepository {

    public List<Role> findAll();

    public Optional<Role> findById(Long id);

    public Role save(Role role);

    public void delete(Role role);

    public void update(Role role);

}
