package com.afb.template.util;

import com.afb.template.config.security.JwtProvider;
import com.afb.template.domain.enumeration.Rolname;
import com.afb.template.domain.model.Users;
import com.afb.template.domain.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestUtil {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private UsersRepository usersRepository;
    public String getUserToken(){
        return request.getHeader("Authorization").substring(7);
    }
    public Integer getUserId(){
        return this.provider.getUserIdFromToken(getUserToken());
    }
    public Boolean isAdmin(){
        Users user = this.usersRepository.findById(this.getUserId()).get();
        return user.getRoles().stream().anyMatch(r -> r.getName().name().equals(Rolname.Role_Admin.name()));
    }
}
