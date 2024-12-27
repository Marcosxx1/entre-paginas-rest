package com.entrepaginas.rest.service.user;

import com.entrepaginas.rest.domain.dto.RegistrationResponse;
import com.entrepaginas.rest.domain.dto.UserRegistrationRequest;

public interface UserService {

    RegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest);
}
