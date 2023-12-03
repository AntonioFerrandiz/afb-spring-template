package com.afb.template.domain.repository;

import com.afb.template.domain.enumeration.Rolname;
import com.afb.template.domain.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(Rolname rolname);
    boolean existsByName(Rolname rolname);
}
