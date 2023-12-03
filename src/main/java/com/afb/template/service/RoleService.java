package com.afb.template.service;

import com.afb.template.domain.enumeration.Rolname;
import com.afb.template.domain.model.Roles;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Roles> getAllRoles();
    Optional<Roles> findByName(Rolname rolname);
    void seed();

}
