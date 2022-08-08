package com.test.ancp.cce.dto.users;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
