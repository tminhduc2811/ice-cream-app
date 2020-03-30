package com.atcud.icecreamapp.repositories.user;

import com.atcud.icecreamapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<User, Long> {
}
