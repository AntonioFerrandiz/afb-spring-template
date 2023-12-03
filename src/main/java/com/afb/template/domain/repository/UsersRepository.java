package com.afb.template.domain.repository;

import com.afb.template.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Optional<Users> getUserByEmail(String email);
    @Query("SELECT u.id FROM Users  u WHERE u.email = ?1")
    Integer getUserIdByEmail(String email);
}
