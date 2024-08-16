package com.seohyeon.bookloan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final SiteUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUserName(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 이름: " + username + "을 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(SiteUser user) {
        // TODO: SiteUser 클래스에 Authority 관련 필드를 추가하고, 해당 필드를 이용하여 권한을 부여해야 함
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");

        return new User(String.valueOf(user.getId()), user.getPassword(), Collections.singleton(grantedAuthority));
    }
}
