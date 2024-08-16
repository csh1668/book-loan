package com.seohyeon.bookloan.user;

import com.seohyeon.bookloan.book.Book;
import com.seohyeon.bookloan.dto.SignUpDto;
import com.seohyeon.bookloan.entity.BaseTimeEntity;
import com.seohyeon.bookloan.loan.Loan;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SiteUser extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, updatable = false, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "loaner")
    @Builder.Default
    public List<Loan> loans = new ArrayList<>();

    public static SiteUser of(SignUpDto dto){
        return SiteUser.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
