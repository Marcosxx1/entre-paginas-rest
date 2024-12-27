package com.entrepaginas.rest.controller;

import com.entrepaginas.rest.domain.dto.RegistrationResponse;
import com.entrepaginas.rest.domain.dto.UserRegistrationRequest;
import com.entrepaginas.rest.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public RegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        RegistrationResponse test = new RegistrationResponse();
        test.setId(1L);
        return test;
    }
}
