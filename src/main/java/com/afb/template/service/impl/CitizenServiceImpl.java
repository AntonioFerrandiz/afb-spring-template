package com.afb.template.service.impl;

import com.afb.template.config.exception.EmailException;
import com.afb.template.config.exception.ForbiddenAccessException;
import com.afb.template.config.exception.ResourceNotFoundException;
import com.afb.template.domain.model.Citizen;
import com.afb.template.domain.repository.CitizenRepository;
import com.afb.template.service.CitizenService;
import com.afb.template.util.DataUtils;
import com.afb.template.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CitizenServiceImpl implements CitizenService {
    private final CitizenRepository citizenRepository;
    private final AuthServiceImpl authService;
    private final RequestUtil requestUtil;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String ENTITY = "Citizen";

    public CitizenServiceImpl(CitizenRepository citizenRepository, AuthServiceImpl authService, RequestUtil requestUtil) {
        this.citizenRepository = citizenRepository;
        this.authService = authService;
        this.requestUtil = requestUtil;
    }
    @Override
    public Citizen save(Citizen citizen) throws Exception {
        Boolean validateEmail = DataUtils.validateEmail(citizen.getEmail());
        if(Boolean.FALSE.equals(validateEmail)) {
            throw new EmailException(citizen.getEmail());
        }
        Boolean existAlias = this.citizenRepository.existsByAlias(citizen.getAlias());
        Boolean existEmail = this.citizenRepository.existsByEmail(citizen.getEmail());
        if(Boolean.TRUE.equals(existAlias)){
            throw new RuntimeException(String.format("Ya existe un usuario registrado con el alias %s", citizen.getAlias()));
        } else if(Boolean.TRUE.equals(existEmail)){
            throw new RuntimeException(String.format("Ya existe un usuario registrado con el email %s", citizen.getEmail()));
        }
        return this.authService.registerCitizen(citizen);
    }
    @Override
    public Citizen update(Citizen req, Integer citizenId) throws RuntimeException {
        Optional<Citizen> optionalCitizen = this.citizenRepository.findById(citizenId);
        if(optionalCitizen.isEmpty()){
            throw new ResourceNotFoundException(ENTITY, citizenId);
        }
        Boolean existAlias = this.citizenRepository.existsByAlias(req.getAlias());
        if(Boolean.TRUE.equals(existAlias)){
            throw new RuntimeException(String.format("Ya existe un usuario registrado con el alias %s", req.getAlias()));
        }
        Citizen citizen = optionalCitizen.get();
        citizen.setAlias(req.getAlias());
        citizen.setBirthdayDate(req.getBirthdayDate());
        return this.citizenRepository.save(citizen);
    }
    @Override
    public Citizen getCitizenById(Integer citizenId) throws ResourceNotFoundException {
        Optional<Citizen> citizen = this.citizenRepository.findById(citizenId);
        if(citizen.isEmpty()){
            throw new ResourceNotFoundException(ENTITY, citizenId);
        }
        return citizen.get();
    }
    @Override
    public List<Citizen> getAllCitizen() throws RuntimeException {
        if(!this.requestUtil.isAdmin()) throw new ForbiddenAccessException();
        List<Citizen> citizenList = this.citizenRepository.findAll();
        if(citizenList.isEmpty()){
            throw new RuntimeException("No se encontró ningun citizen");
        }
        return citizenList;
    }
}
