package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.User;

public interface UserRepository {

    public List<User> findAll();

    public Optional<User> findById(Long id);

    public boolean isExist(String userName);

    public User findUserByUsername(String userName);

    public User save(User user);

    public void delete(User user);

    public void update(User user);

}
