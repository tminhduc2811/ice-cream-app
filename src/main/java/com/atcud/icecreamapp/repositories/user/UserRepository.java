package com.atcud.icecreamapp.repositories.user;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {

    Page<User> findPage(Pageable pageable);

    List<User> findAll();

    User findById(Long id);

    boolean isExist(String userName);

    User findUserByUsername(String userName);

    User save(User user);

    void delete(User user);

    User update(User user);

}
