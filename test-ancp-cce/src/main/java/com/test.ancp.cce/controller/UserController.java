package com.test.ancp.cce.controller;

import com.test.ancp.cce.dto.users.UserDto;
import com.test.ancp.cce.dto.users.UserRequest;
import com.test.ancp.cce.service.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("user.v1.crud")
@RequestMapping("v1/user")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userUuid}")
    ResponseEntity<UserDto> show(@PathVariable UUID userUuid) {
        return ResponseEntity.ok().body(userService.findByUuid(userUuid));
    }

    @PostMapping
    ResponseEntity<UserDto> create(@RequestBody UserRequest request){
        return new ResponseEntity<>(userService.save(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{userUuid}")
    UserDto update(@PathVariable  UUID userUuid, @RequestBody UserRequest request){
        return userService.update(userUuid, request);
    }

    @DeleteMapping("/{userUuid}")
    void delete(@PathVariable UUID userUuid){
        userService.delete(userUuid);
    }
}
