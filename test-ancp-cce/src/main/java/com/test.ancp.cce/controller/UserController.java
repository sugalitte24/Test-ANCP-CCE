package com.test.ancp.cce.controller;

import com.test.ancp.cce.dto.users.LoginDto;
import com.test.ancp.cce.dto.users.TokenDto;
import com.test.ancp.cce.dto.users.UserDto;
import com.test.ancp.cce.dto.users.UserRequest;
import com.test.ancp.cce.service.users.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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

    @PostMapping("/login")
    TokenDto login(@RequestBody LoginDto login){
        String loginService = userService.login(login);
        TokenDto tokenDto = new TokenDto();
        if(loginService.equals("OK")){
            getJWTToken(login.getEmail(), tokenDto);
            return tokenDto;
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    private void getJWTToken(String username, TokenDto tokenDto) {
        String secretKey = "f63j8dk3l7";
        String token = Jwts
                .builder()
                .setId("ancpJWT")
                .setSubject(username)
                .claim("authorities","ROLE_USER")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        tokenDto.setToken(token);
        tokenDto.setStartDate(Date.from(Instant.now()));
        tokenDto.setExpirationDate(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)));
        tokenDto.setTypeToken("Bearer");

    }
}
