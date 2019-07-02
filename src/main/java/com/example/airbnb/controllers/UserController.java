package com.example.airbnb.controllers;

import java.util.List;

import com.example.airbnb.entities.User;
import com.example.airbnb.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
	//@GetMapping(path="/users", produces="application/json")
    //public List<User> displayUsers() {
    //    return userRepository.getAllUsers();
    //}

    @GetMapping(path="/users", produces="application/json")
    public List<User> displayUsersByEmail(@RequestParam String email) {
        return userRepository.getUserByEmail(email);
    }

    @GetMapping(path="/create_user")
    public void addUser(){
      //userRepository.addUser("TEST", "TEST", "TEST", "12345");
    }

    @GetMapping(path="/update_user")
    public void updateUser(){
    // userRepository.updateUser("TEST", "New TEST");
    }

    @GetMapping(path="/delete_user")
    public void deleteUser(){
    //  userRepository.deleteUser(1002);
    }

    @PostMapping(value="/users", produces="application/json")
    public User createUser(@RequestBody User user) {
        userRepository.createUser(user);
        return user;
    }

    @PostMapping(value="/users/{id}", produces="application/json")
    public User updateUser(@RequestBody User user, @PathVariable("id") int userId) {
        userRepository.updateUser(user, userId);
        return user;
    }
}
