package com.test.ancp.cce.dto.users;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    public String email;
    public String password;
}
