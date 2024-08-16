package com.seohyeon.bookloan.user;

import com.seohyeon.bookloan.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseDto<?> signUp(@RequestBody SiteUserRequestDto requestBody,
                                         BindingResult bindingResult) {
        try {
            return ResponseDto.ok(this.authService.signup(requestBody));
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody SiteUserRequestDto requestBody) {
        try {
            return ResponseDto.ok(this.authService.login(requestBody));
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseDto<?> refresh(@RequestBody JwtTokenDto requestBody) {
        try {
            return ResponseDto.ok(this.authService.refresh(requestBody));
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }
}
