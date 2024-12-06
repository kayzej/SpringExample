package com.kayzej.user.controller;

import com.kayzej.user.model.Users;
import com.kayzej.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    // GET endpoint to fetch all example objects
    @GetMapping
    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable Long id) {
        return repository.findById(id);
    }

    // POST endpoint to create a new example object
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users Users) {
        Users savedObject = repository.save(Users);
        return ResponseEntity.ok(savedObject);
    }
}