package org.example.bakcendspring.controllers;

import lombok.RequiredArgsConstructor;
import org.example.bakcendspring.dto.filters.FilterUserDto;
import org.example.bakcendspring.dto.request.UserRequest;
import org.example.bakcendspring.dto.response.UserResponse;
import org.example.bakcendspring.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers(@RequestBody FilterUserDto filter) {
        return userService.getAll(filter);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody @Validated UserRequest userRequest) {
        userService.create(userRequest);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody @Validated UserRequest userRequest, @PathVariable Long id) {
        userService.update(userRequest, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        userService.delete(id);
    }
}
