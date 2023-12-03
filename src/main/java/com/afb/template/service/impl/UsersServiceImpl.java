package com.afb.template.service.impl;

import com.afb.template.config.exception.ResourceNotFoundException;
import com.afb.template.domain.model.Users;
import com.afb.template.domain.repository.UsersRepository;
import com.afb.template.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public Optional<Users> getUserByEmail(String email) {
        Optional<Users> optionalUser = this.usersRepository.getUserByEmail(email);
        if(optionalUser.isEmpty()){
            throw new ResourceNotFoundException("Usuario", email);
        }
        return optionalUser;
    }
}
