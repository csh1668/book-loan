package com.seohyeon.bookloan.user;

import com.seohyeon.bookloan.dto.ResponseDto;
import com.seohyeon.bookloan.dto.SiteUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class SiteUserController {
    private final SiteUserService siteUserService;

    @GetMapping("/me")
    public ResponseDto<?> getMyInfo(){
        try {
            var sec = siteUserService.getMyInfoBySecurity();
            return ResponseDto.ok(sec);
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

}
