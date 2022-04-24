package com.example.demo.controller;

import com.example.demo.auth.*;
import com.example.demo.common.InvalidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthService service;
    @Autowired
    public AuthUtilsService utils;

    @PostMapping
    public UUID login(@Valid @RequestBody LoginData loginData, HttpServletResponse response) throws NotAuthorized {
        var uuid = service.login(loginData).orElseThrow(NotAuthorized::new);
        utils.add(response, uuid);
        return uuid;
    }

    @DeleteMapping("")
    public void logout(HttpServletRequest request) throws InvalidRequest {
        utils.get(request).ifPresent(uuid ->service.remove(uuid));
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterData registerData) throws InvalidRequest {
        try {
            service.register(registerData);
        } catch (UsernameTaken e) {
            throw new InvalidRequest();
        }
    }

}
