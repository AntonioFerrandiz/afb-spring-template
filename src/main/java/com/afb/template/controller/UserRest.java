package com.afb.template.controller;

import com.afb.template.domain.dto.Admin.AdminResource;
import com.afb.template.domain.dto.Admin.CreateAdminResource;
import com.afb.template.domain.mapping.AdminMapper;
import com.afb.template.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserRest {
    private final AdminService adminService;
    private final AdminMapper adminMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserRest(AdminService adminService, AdminMapper adminMapper) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
    }
    @PostMapping(path = "/save-admin", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AdminResource> saveAdmin(@RequestBody CreateAdminResource resource) throws Exception {
        AdminResource admin = adminMapper.toResource(this.adminService.save(adminMapper.toModel(resource)));
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }


}
