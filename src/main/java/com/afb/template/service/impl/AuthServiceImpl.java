package com.afb.template.service.impl;


import com.afb.template.config.exception.ForbiddenAccessException;
import com.afb.template.config.exception.ResourceNotFoundException;
import com.afb.template.config.security.JwtProvider;
import com.afb.template.domain.dto.Jwt.ChangePasswordResource;
import com.afb.template.domain.dto.Jwt.LoginResource;
import com.afb.template.domain.dto.Jwt.TokenResource;
import com.afb.template.domain.enumeration.Rolname;
import com.afb.template.domain.model.*;
import com.afb.template.domain.repository.*;
import com.afb.template.service.AuthService;
import com.afb.template.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {
    private final CitizenRepository citizenRepository;
    private final AdminRepository adminRepository;
    private final RolesRepository roleRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider provider;
    private final RequestUtil requestUtil;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthServiceImpl(CitizenRepository citizenRepository, AdminRepository adminRepository, RolesRepository roleRepository, UsersRepository usersRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider provider, RequestUtil requestUtil) {
        this.citizenRepository = citizenRepository;
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.provider = provider;
        this.requestUtil = requestUtil;
    }

    @Override
    public Roles getCitizenRole() throws Exception {
        Optional<Roles> optionalRole = this.roleRepository.findByName(Rolname.Role_Citizen);
        if(optionalRole.isEmpty()){
            throw new ResourceNotFoundException(String.format("No existe un role llamado %s", Rolname.Role_Citizen));
        }
        return optionalRole.get();
    }

    @Override
    public Roles getAdminRole() throws Exception {
        Optional<Roles> optionalRole = this.roleRepository.findByName(Rolname.Role_Admin);
        if(optionalRole.isEmpty()){
            throw new ResourceNotFoundException(String.format("No existe un role llamado %s", Rolname.Role_Admin));
        }
        return optionalRole.get();
    }

    @Override
    public Citizen registerCitizen(Citizen request) throws Exception {
        Set<Roles> roleSet = new HashSet<>();
        roleSet.add(getCitizenRole());
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setRoles(roleSet);
        return citizenRepository.save(request);
    }

    @Override
    public Admin registerAdmin(Admin request) throws Exception {
        Set<Roles> roleSet = new HashSet<>();
        roleSet.add(getAdminRole());
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setRoles(roleSet);
        return adminRepository.save(request);
    }

    @Override
    public TokenResource login(LoginResource loginResource) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginResource.getEmail(), loginResource.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = String.format("Bearer %s", provider.generateToken(authentication));
        TokenResource tokenResource = new TokenResource(token);
        return tokenResource;
    }


    @Override
    public String updatePassword(ChangePasswordResource request) throws Exception {
        String message = "";
        Optional<Users> optionalUser = this.usersRepository.getUserByEmail(request.getEmail());
        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("Usuario", request.getEmail());
        }
        if(!Objects.equals(optionalUser.get().getId(), this.requestUtil.getUserId())) throw new ForbiddenAccessException();
        Users user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        message = String.format("Se actualizó correctamente la contraseña del usuario con id %d", user.getId());
        return message;
    }
}
