package com.afb.template.service.impl;


import com.afb.template.domain.enumeration.Rolname;
import com.afb.template.domain.model.Roles;
import com.afb.template.domain.repository.RolesRepository;
import com.afb.template.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    private final RolesRepository roleRepository;
    private static final String[] DEFAULT_ROLES = { "Role_Citizen","Role_Admin"};

    public RoleServiceImpl(RolesRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Optional<Roles> findByName(Rolname rolname) {
        return this.roleRepository.findByName(rolname);
    }
    @Override
    public void seed() {
        Arrays.stream(DEFAULT_ROLES).forEach(name -> {
            Rolname roleName = Rolname.valueOf(name);
            if(!roleRepository.existsByName(roleName)) {
                roleRepository.save((new Roles()).withName(roleName));
            }
        } );
    }
}
