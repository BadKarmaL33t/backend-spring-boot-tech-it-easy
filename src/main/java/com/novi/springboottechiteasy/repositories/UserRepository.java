package com.novi.springboottechiteasy.repositories;

import com.novi.springboottechiteasy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
