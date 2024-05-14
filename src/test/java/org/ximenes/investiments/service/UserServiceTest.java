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
import org.ximenes.investiments.dto.user.UpdateUserDTO;
import org.ximenes.investiments.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Nested
    class listUsers {

        @Test @DisplayName("Should list all successful users.")
        void shouldReturnAllUserWithSuccess(){
            User user = new User();
            user.setUserId(UUID.randomUUID());
            user.setUsername("username");
            user.setEmail("email@gmail.com");
            user.setPassword("password");

            doReturn(List.of(user)).when(userRepository).findAll();
            var output = userService.listUsers();
            assertNotNull(output);
            assertEquals(1, output.size());
        }
    }

    @Nested
    class deleteUser{

        @Test @DisplayName("Should delete a user successfully when user exists.")
        void shoudDeleteAUserSuccessfully(){
            User user = new User();
            user.setUserId(UUID.randomUUID());

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doNothing().when(userRepository).deleteById(uuidArgumentCaptor.capture());

            userService.deleteUserById(user.getUserId().toString());

            var idList = uuidArgumentCaptor.getAllValues();

            assertEquals(user.getUserId(), idList.get(0));
            assertEquals(user.getUserId(), idList.get(1));
            verify(userRepository, times(1)).findById(idList.get(0));
            verify(userRepository, times(1)).deleteById(idList.get(1));
        }
        @Test @DisplayName("Should not delete a user if it does not exists.")
        void shoudNotDeleteUserById(){
            User user = new User();
            user.setUserId(UUID.randomUUID());

            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());

            assertThrows(RuntimeException.class, ()-> {
                userService.deleteUserById(user.getUserId().toString());
            });

            assertEquals(user.getUserId(),uuidArgumentCaptor.getValue());
            verify(userRepository, times(1)).findById(uuidArgumentCaptor.getValue());
            verify(userRepository, times(0)).deleteById(any());
        }
    }

    @Nested
    class updateUserById{
        @Test @DisplayName("Should update a user exists and username and password is filled")
        void shouldUpdateUserByIdWhenUserExistsUsernameAndPasswordIsFilled(){

            User user = new User();
            user.setUserId(UUID.randomUUID());
            user.setUsername("username");
            user.setEmail("email@gmail.com");
            user.setPassword("password");

            UpdateUserDTO updateUserDTO = new UpdateUserDTO("newUsername", "newPassword");

            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());

            userService.updateUserById(user.getUserId().toString(), updateUserDTO);

            assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());
            var userCapture = userArgumentCaptor.getValue();
            assertEquals(user.getUsername(), userCapture.getUsername());
            assertEquals(user.getPassword(), userCapture.getPassword());
            verify(userRepository, times(1)).findById(uuidArgumentCaptor.capture());
            verify(userRepository, times(1)).save(user);
        }

        @Test @DisplayName("Should not update a user not exists")
        void shouldNotUpdateUserByIdWhenUserExistsUsernameAndPasswordIsFilled(){

            User user = new User();
            user.setUserId(UUID.randomUUID());

            UpdateUserDTO updateUserDTO = new UpdateUserDTO("newUsername", "newPassword");

            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());

            assertThrows(RuntimeException.class, ()-> {userService.updateUserById(user.getUserId().toString(), updateUserDTO);});
            assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());
            verify(userRepository, times(1)).findById(uuidArgumentCaptor.capture());
            verify(userRepository, times(0)).save(user);
        }
    }

}