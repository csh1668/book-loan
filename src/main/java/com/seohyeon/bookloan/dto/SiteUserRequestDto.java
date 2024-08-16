package com.seohyeon.bookloan.dto;

import com.seohyeon.bookloan.user.SiteUser;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiteUserRequestDto {
    private String username;
    private String password;
    private String email;

    public SiteUser toEntity(PasswordEncoder passwordEncoder){
        return SiteUser.builder()
                .userName(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
