package com.seohyeon.bookloan.user;

import com.seohyeon.bookloan.dto.JwtTokenDto;
import com.seohyeon.bookloan.dto.SiteUserRequestDto;
import com.seohyeon.bookloan.dto.SiteUserResponseDto;
import com.seohyeon.bookloan.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final SiteUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public SiteUserResponseDto signup(SiteUserRequestDto requestDto){
        if (userRepository.existsByUserName(requestDto.getUsername()))
            throw new RuntimeException("이미 존재하는 사용자 이름입니다.");

        var user = requestDto.toEntity(passwordEncoder);
        return SiteUserResponseDto.of(userRepository.save(user));
    }

    public JwtTokenDto login(SiteUserRequestDto requestDto){
        var token = requestDto.toAuthentication();
        var authentication = managerBuilder.getObject().authenticate(token);
        return jwtProvider.generateToken(authentication);
    }

    public JwtTokenDto refresh(JwtTokenDto requestBody) {
        return null; // TODO: refresh token 구현
    }
}
