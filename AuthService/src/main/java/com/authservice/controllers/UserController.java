package com.authservice.controllers;

import com.authservice.models.User;
import com.authservice.services.Impl.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        boolean hasAccess = true;
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/{id}")

    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(Math.toIntExact(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        userService.deleteById(Math.toIntExact(id));
        return ResponseEntity.ok().build();
    }
}
