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
import org.ximenes.investiments.domain.user.exception.UserNotFoundException;
import org.ximenes.investiments.dto.user.CreateUserDTO;
import org.ximenes.investiments.repository.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Nested
    class createUser {

        @Test @DisplayName("Should create a user with success")
        void shouldCreateAUserWithSuccess() {
            var user = new User();
            user.setUsername("username");
            user.setEmail("email@gmail.com");
            user.setPassword("password");
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
            var input = new CreateUserDTO(
                    "username",
                    "email@email.com",
                    "password"
            );
            var output = userService.create(input);
            var userCaptured = userArgumentCaptor.getValue();
            assertNotNull(output);
            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
        }

        @Test @DisplayName("Should throw exception when error occurs.")
        void shouldThrowExceptionWhenOccurs(){

            doThrow(new RuntimeException()).when(userRepository).save(any());
            CreateUserDTO input = new CreateUserDTO(
                    "username",
                    "email@email.com",
                    "123"
            );
            assertThrows(RuntimeException.class, ()-> userService.create(input));
        }

    }

    @Nested
    class getUserById{

        @Test @DisplayName("Should find a user by id successfully.")
        void shouldGetUserByIdWithSuccess(){

            User user = new User();
            user.setUserId(UUID.randomUUID());
            user.setUsername("username");
            user.setEmail("email@gmail.com");
            user.setPassword("password");

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            userService.getUserById(user.getUserId().toString());
            assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());
        }

        @Test @DisplayName("Should throw an exception if a user is not found.")
        void shouldGetUserByIdWithNotExists(){

           var user = UUID.randomUUID();
           doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());

           assertThrows(RuntimeException.class, ()-> {
                userService.getUserById(user.toString());
           });
        }
    }
}