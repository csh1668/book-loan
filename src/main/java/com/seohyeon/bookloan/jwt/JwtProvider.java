package com.seohyeon.bookloan.jwt;

import com.seohyeon.bookloan.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    @Value("${service.jwt.refresh-expiration}")
    private Long refreshExpiration;

    private final SecretKey secretKey;

    public JwtProvider(@Value("${service.jwt.secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public JwtTokenDto generateToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        var now = new Date();

        var accessToken = Jwts.builder()
                .subject(authentication.getName())
                .claim("auth", authorities)
                .issuer(issuer)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + accessExpiration))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();

        var refreshToken = Jwts.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + refreshExpiration))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();

        return JwtTokenDto.builder()
                .grantType("bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtTokenDto refreshToken(JwtTokenDto tokenDto){
        return null; // TODO: refresh token 구현
    }

    public Authentication getAuthentication(String accessToken){
        var claims = parseClaims(accessToken);

        if (claims == null || claims.get("auth") == null) throw new RuntimeException("권한 정보가 없는 토큰입니다.");

        var authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean isValidToken(String jwtToken){
        try {
            var c = parseClaims(jwtToken);
            return c != null;
        } catch (Exception e){
            // 억재
        }
        return false;
    }

    private Claims parseClaims(String jwtToken){
        try {
            return Jwts.parser().verifyWith(secretKey).build()
                    .parseSignedClaims(jwtToken).getPayload();
        } catch (Exception e){
            return null;
        }
    }

    private boolean isExpiredToken(String jwtToken){
        try {
            var claims = Jwts.parser().verifyWith(secretKey).build()
                    .parseSignedClaims(jwtToken).getPayload();
            return claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e){
            return true;
        } catch (Exception e){
            return false;
        }
    }
}