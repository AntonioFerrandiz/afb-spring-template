package com.afb.template.config.security;

import com.afb.template.service.RoleService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeedingConfig {
    private final RoleService roleService;

    public DatabaseSeedingConfig(RoleService roleService) {
        this.roleService = roleService;
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) throws Exception {
        roleService.seed();
    }
}
