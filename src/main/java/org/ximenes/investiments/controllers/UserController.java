package org.ximenes.investiments.controllers;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ximenes.investiments.dto.user.*;
import org.ximenes.investiments.domain.user.User;
import org.ximenes.investiments.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Data
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody CreateUserDTO body){
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDTO(this.userService.create(body)));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id){
        User user = this.userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        List<User> users = this.userService.listUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UpdateUserDTO body){
        this.userService.updateUserById(id, body);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id){
        this.userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/accounts")
    public ResponseEntity<Void> createUserAccount(@PathVariable String id, @RequestBody CreateAccountDTO body){
        userService.createAccount(id, body);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> listAccounts(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.listAccount(id));

    }

}
