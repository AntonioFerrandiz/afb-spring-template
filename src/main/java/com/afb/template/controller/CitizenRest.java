package com.afb.template.controller;

import com.afb.template.domain.dto.Citizen.CitizenResource;
import com.afb.template.domain.dto.Citizen.CreateCitizenResource;
import com.afb.template.domain.dto.Citizen.UpdateCitizenResource;
import com.afb.template.domain.mapping.CitizenMapper;
import com.afb.template.service.CitizenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/citizen")
public class CitizenRest {
    private final CitizenService citizenService;

    private final CitizenMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CitizenRest(CitizenService citizenService, CitizenMapper mapper) {
        this.citizenService = citizenService;
        this.mapper = mapper;
    }

    @PostMapping(path = "/saveCitizen", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CitizenResource> saveCitizen(@RequestBody CreateCitizenResource resource) throws Exception {
        CitizenResource citizen = mapper.toResource(this.citizenService.save(mapper.toModel(resource)));
        return new ResponseEntity<>(citizen, HttpStatus.OK);
    }

    @PostMapping(path = "/updateCitizen/{citizenId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CitizenResource> updateCitizen(@RequestBody UpdateCitizenResource resource, @PathVariable Integer citizenId) throws Exception{
        CitizenResource citizen = mapper.toResource(this.citizenService.update(mapper.toModel(resource), citizenId));
        return new ResponseEntity<>(citizen, HttpStatus.OK);
    }

    @GetMapping(path = "/getCitizenById/{citizenId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CitizenResource> getCitizenById(@PathVariable Integer citizenId) throws Exception {
        CitizenResource citizen = mapper.toResource(this.citizenService.getCitizenById(citizenId));
        return new ResponseEntity<>(citizen, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllCitizen", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CitizenResource>> getAllCitizen() throws Exception {
        List<CitizenResource> citizenList = mapper.modelListToList(this.citizenService.getAllCitizen());
        return new ResponseEntity<>(citizenList, HttpStatus.OK);
    }
}
