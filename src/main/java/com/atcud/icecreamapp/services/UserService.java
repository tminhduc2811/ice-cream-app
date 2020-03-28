package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.UserUpdateDTO;
import com.atcud.icecreamapp.entities.Role;
import com.atcud.icecreamapp.entities.User;

public interface UserService {

    public List<User> getAllUsers();

    public Optional<User> getUserById(Long id);

    public User findUserByUsername(String username);

    public String login(String username, String password);

    public User register(User user);

    public void delete(Long id);

    public User update(UserUpdateDTO user);

    public List<Role> getUserRoles(Long id);

    public void updateUserRoles(Long id, List<Long> roleIds);
}
