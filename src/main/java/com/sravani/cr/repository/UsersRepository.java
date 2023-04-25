package com.sravani.cr.repository;

import com.sravani.cr.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, String> {
    List<Users> findByUsername(String username);
}
