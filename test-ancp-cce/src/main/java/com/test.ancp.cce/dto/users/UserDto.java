package com.test.ancp.cce.dto.users;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    public UUID uuid;
    public LocalDateTime createdAt;
    public LocalDateTime lastModifiedAt;
    private String name;
    private String lastName;
    private String email;
    private String password;
}
