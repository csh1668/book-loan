package com.seohyeon.bookloan.dto;

import com.seohyeon.bookloan.user.SiteUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiteUserResponseDto {
    private String userName;

    public static SiteUserResponseDto of(SiteUser user){
        return SiteUserResponseDto.builder()
                .userName(user.getUserName())
                .build();
    }
}
