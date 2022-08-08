package com.test.ancp.cce.service.users;

import com.test.ancp.cce.dto.users.UserDto;
import com.test.ancp.cce.dto.users.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> findAll();
    UserDto findByUuid(UUID userUuid);
    UserDto save(UserRequest request);
    UserDto update(UUID userUuid, UserRequest request);
    void delete( UUID userUuid);
}
