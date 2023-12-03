package com.afb.template.service;


import com.afb.template.domain.dto.Jwt.ChangePasswordResource;
import com.afb.template.domain.dto.Jwt.LoginResource;
import com.afb.template.domain.dto.Jwt.TokenResource;
import com.afb.template.domain.model.Admin;
import com.afb.template.domain.model.Citizen;
import com.afb.template.domain.model.Roles;

public interface AuthService {
    Roles getCitizenRole() throws Exception;
    Roles getAdminRole() throws Exception;
    Citizen registerCitizen(Citizen request) throws Exception;
    Admin registerAdmin(Admin request) throws Exception;
    TokenResource login(LoginResource loginResource) throws Exception;
    String updatePassword(ChangePasswordResource request) throws Exception;
}
