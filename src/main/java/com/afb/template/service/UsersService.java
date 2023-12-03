package com.afb.template.service;


import com.afb.template.domain.model.Users;

import java.util.Optional;

public interface UsersService {
    Optional<Users> getUserByEmail(String email);
}
