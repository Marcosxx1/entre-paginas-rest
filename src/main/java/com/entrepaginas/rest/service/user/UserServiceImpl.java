package com.entrepaginas.rest.service.user;

import com.entrepaginas.rest.domain.dto.RegistrationResponse;
import com.entrepaginas.rest.domain.dto.UserRegistrationRequest;
import com.entrepaginas.rest.domain.entity.User;
import com.entrepaginas.rest.repository.user.UserRepository;
import com.entrepaginas.rest.utils.BuildExceptionMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public RegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        return null;
    }

    private User existByCpf(String cpf) {
        return userRepository.findByCpf(cpf).orElseThrow(() -> BuildExceptionMessages.notFoundUserException(cpf));
    }
}
