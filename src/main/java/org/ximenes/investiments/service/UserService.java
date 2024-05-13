package org.ximenes.investiments.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.ximenes.investiments.domain.user.User;
import org.ximenes.investiments.domain.user.exception.UserAlreadyExistsException;
import org.ximenes.investiments.domain.user.exception.UserNotFoundException;
import org.ximenes.investiments.dto.user.CreateUserDTO;
import org.ximenes.investiments.dto.user.UpdateUserDTO;
import org.ximenes.investiments.repository.UserReposity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class UserService {

    private final UserReposity userReposity;

    public UUID create(CreateUserDTO body){
        Optional<User> user = this.userReposity.findByEmail(body.email());
        if (user.isPresent()) throw new UserAlreadyExistsException("User already exists.");

        User newUser = new User();
            newUser.setUsername(body.username());
            newUser.setEmail(body.email());
            newUser.setPassword(body.password());
            this.userReposity.save(newUser);

        return newUser.getUserId();
    }

    public User getUserById(String id){
        return this.userReposity.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not Found."));
    }

    public List<User> listUsers(){
        return this.userReposity.findAll();
    }

    public void updateUserById(String id, UpdateUserDTO body) {
        User user = this.userReposity.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not Found."));
            if (body.username() != null) {
                user.setUsername(body.username());
            }
            if (body.password() != null) {
                user.setPassword(body.password());
            }
            this.userReposity.save(user);
        }


    public void deleteUserById(String id){
        this.userReposity.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not Found."));
        this.userReposity.deleteById(UUID.fromString(id));
    }
}
