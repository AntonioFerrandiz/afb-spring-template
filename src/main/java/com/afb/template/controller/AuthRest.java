package com.afb.template.controller;

import com.afb.template.domain.dto.Jwt.ChangePasswordResource;
import com.afb.template.domain.dto.Jwt.LoginResource;
import com.afb.template.domain.dto.Jwt.TokenResource;
import com.afb.template.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthRest {
    private final AuthService authService;

    public AuthRest(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TokenResource> login(@Valid @RequestBody LoginResource loginResource) throws Exception{
        TokenResource tokenResource = this.authService.login(loginResource);
        return new ResponseEntity<>(tokenResource, HttpStatus.OK);
    }
    @PostMapping(path = "/changePassword", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordResource request) throws Exception{
        String message = this.authService.updatePassword(request);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
