package com.test.ancp.cce.dto.users;

import com.test.ancp.cce.model.User;
import org.mapstruct.*;

import java.util.List;
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {
    User toModel(UserRequest request);

    UserDto toDtoFromModel(User user);

    List<UserDto> toDtoList(List<User> userList);

    void update(UserRequest request, @MappingTarget User user);
}
