package com.seohyeon.bookloan.dto;

import com.seohyeon.bookloan.user.SiteUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String token;
    private int exprTime;
    private String userName;
}
