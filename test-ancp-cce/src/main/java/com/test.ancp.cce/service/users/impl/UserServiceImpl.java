package com.test.ancp.cce.service.users.impl;

import com.test.ancp.cce.dto.users.UserDto;
import com.test.ancp.cce.dto.users.UserMapper;
import com.test.ancp.cce.dto.users.UserRequest;
import com.test.ancp.cce.model.User;
import com.test.ancp.cce.repository.UserRepository;
import com.test.ancp.cce.service.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> response  = userRepository.findAll();
        return userMapper.toDtoList(response);
    }

    @Transactional(readOnly = true)
    public UserDto findByUuid(UUID productUuid) {
        Optional<User> response = Optional.ofNullable(userRepository.findById(productUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no Encontrado")));
        UserDto UserDto = new UserDto();
        if(response.isPresent()){
            UserDto = userMapper.toDtoFromModel(response.get());
        }
        return UserDto;
    }

    @Transactional
    public UserDto save(UserRequest request) {
        List<UserDto> users = findAll();
        for(UserDto user : users){
            if(user.getEmail().equals(request.getEmail())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo del usuario ya existe.");
            }
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        request.setPassword(encoder.encode(request.getPassword()));
        User model = userMapper.toModel(request);
        User save = userRepository.save(model);
        return userMapper.toDtoFromModel(save);
    }

    @Transactional
    public UserDto update(UUID productUuid, UserRequest request) {
        Optional<User> model = userRepository.findById(productUuid);
        if(model.isPresent()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            request.setPassword(encoder.encode(request.getPassword()));
            userMapper.update(request,model.get());
            return userMapper.toDtoFromModel(userRepository.save(model.get()));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no Encontrado");
        }
    }

    @Transactional
    public void delete(UUID productUuid) {
        Optional<User> model = userRepository.findById(productUuid);
        if(model.isPresent()){
            userRepository.deleteById(productUuid);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no Encontrado");
        }
    }
}
