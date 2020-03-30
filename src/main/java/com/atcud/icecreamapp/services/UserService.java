package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.UserDTO;
import com.atcud.icecreamapp.DTO.entities.UserUpdateDTO;
import com.atcud.icecreamapp.entities.Role;
import com.atcud.icecreamapp.entities.User;

public interface UserService {

    public List<UserDTO> getAllUsers();

    public UserDTO getUserById(Long id);

    public UserDTO findUserByUsername(String username);

    public String login(String username, String password);

    public UserDTO register(User user);

    public void delete(Long id);

    public UserDTO update(UserUpdateDTO user);

    public List<Role> getUserRoles(Long id);

    public void updateUserRoles(Long id, List<Long> roleIds);

    public boolean isExist(String username);
}
