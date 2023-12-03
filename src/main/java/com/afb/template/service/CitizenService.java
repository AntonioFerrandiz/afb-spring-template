package com.afb.template.service;

import com.afb.template.domain.model.Citizen;

import java.util.List;

public interface CitizenService {
    Citizen save(Citizen citizen) throws Exception;
    Citizen update(Citizen citizen, Integer citizenId) throws Exception;
    Citizen getCitizenById(Integer citizenId) throws Exception;
    List<Citizen> getAllCitizen() throws Exception;
}
