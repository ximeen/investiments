package org.ximenes.investiments.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ximenes.investiments.dto.CreateUserDTO;
import org.ximenes.investiments.domain.User.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserDTO body){
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id){
        return null;
    }
}
