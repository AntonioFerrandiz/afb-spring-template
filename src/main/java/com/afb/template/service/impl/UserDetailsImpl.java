package com.afb.template.service.impl;


import com.afb.template.domain.model.PrincipalUser;
import com.afb.template.domain.model.Users;
import com.afb.template.service.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsImpl implements UserDetailsService {
    private final UsersService userService;
    public UserDetailsImpl(UsersService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userService.getUserByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException(String.format("No se encontró ningun usuario con email %s", email));
        }
        return PrincipalUser.build(optionalUser.get());
    }
}
