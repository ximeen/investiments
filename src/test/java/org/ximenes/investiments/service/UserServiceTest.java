package org.ximenes.investiments.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ximenes.investiments.domain.user.User;
import org.ximenes.investiments.dto.user.CreateUserDTO;
import org.ximenes.investiments.repository.UserRepository;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Nested
    class createUser {

        @Test @DisplayName("Should create a user with success")
        void shouldCreateAUserWithSuccess() {

            // Arrange
            var user = new User();
            user.setUsername("username");
            user.setEmail("email@gmail.com");
            user.setPassword("password");
            doReturn(user).when(userRepository).save(any());
            var input = new CreateUserDTO(
                    "username",
                    "email@email.com",
                    "123"
            );

            // Act
            var output = userService.create(input);

            // Assert
            assertNotNull(output);


        }
    }
}