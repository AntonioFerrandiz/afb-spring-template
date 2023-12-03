package com.afb.template.service.impl;

import com.afb.template.config.exception.EmailException;
import com.afb.template.config.exception.ForbiddenAccessException;
import com.afb.template.domain.model.Admin;
import com.afb.template.domain.repository.AdminRepository;
import com.afb.template.service.AdminService;
import com.afb.template.util.DataUtils;
import com.afb.template.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AuthServiceImpl authService;
    private final RequestUtil requestUtil;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AdminServiceImpl(AdminRepository adminRepository, AuthServiceImpl authService, RequestUtil requestUtil) {
        this.adminRepository = adminRepository;
        this.authService = authService;
        this.requestUtil = requestUtil;
    }

    @Override
    public Admin save(Admin admin) throws Exception {
        if(!this.requestUtil.isAdmin()) throw new ForbiddenAccessException();
        Boolean validateEmail = DataUtils.validateEmail(admin.getEmail());
        if(Boolean.FALSE.equals(validateEmail)) {
            throw new EmailException(admin.getEmail());
        }
        Boolean existAlias = this.adminRepository.existsByAlias(admin.getAlias());
        Boolean existEmail = this.adminRepository.existsByEmail(admin.getEmail());
        if(Boolean.TRUE.equals(existAlias)){
            throw new RuntimeException(String.format("Ya existe un admin registrado con el alias %s", admin.getAlias()));
        } else if(Boolean.TRUE.equals(existEmail)){
            throw new RuntimeException(String.format("Ya existe un admin registrado con el email %s", admin.getEmail()));
        }
        return this.authService.registerAdmin(admin);
    }
}
