package com.seohyeon.bookloan.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class SignUpDto {
    @Size(min = 3, max = 20)
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordCheck;
    @NotEmpty
    @Email
    private String email;
}
