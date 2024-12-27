package com.entrepaginas.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.entrepaginas.rest.domain.dto.RegistrationResponse;
import com.entrepaginas.rest.domain.dto.UserRegistrationRequest;
import com.entrepaginas.rest.domain.entity.User;
import com.entrepaginas.rest.repository.user.UserRepository;
import com.entrepaginas.rest.service.user.UserService;
import com.entrepaginas.rest.service.user.UserServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserServiceImpl.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testRegisterUser_whenUserExists_thenReturnExistingUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("existingUser");

        when(userRepository.findByCpf(anyString())).thenReturn(Optional.of(existingUser));

        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setName("João Alfresco Goliath");
        request.setEmail("joao_alfresco_goliath@email.com");
        request.setCpf("12345678901");
        request.setPassword("1234567890");
        request.setRepeatPassword("1234567890");

        RegistrationResponse response = userService.registerUser(request);

        verify(userRepository, never()).save(any());

        assertEquals(existingUser.getId(), response.getId());
    }
}
