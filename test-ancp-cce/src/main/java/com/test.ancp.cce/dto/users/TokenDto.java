package com.test.ancp.cce.dto.users;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String token;
    private Date startDate;
    private Date expirationDate;
    private String typeToken;
}
