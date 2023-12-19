package com.afb.template.domain.repository;

import com.afb.template.domain.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Boolean existsByAlias(String alias);
    Boolean existsByEmail(String email);
    Optional<Admin> findAdminByAlias(String alias);

}
