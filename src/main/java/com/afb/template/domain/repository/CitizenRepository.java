package com.afb.template.domain.repository;

import com.afb.template.domain.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
    Boolean existsByAlias(String alias);
    Boolean existsByEmail(String email);

    Citizen getCitizenById(Integer citizenId);

    Optional<Citizen> findCitizenByAlias(String alias);
}
