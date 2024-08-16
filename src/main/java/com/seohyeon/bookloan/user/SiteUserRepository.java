package com.seohyeon.bookloan.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteUserRepository extends JpaRepository<SiteUser, String> {

    Optional<SiteUser> findById(Long id);
    Optional<SiteUser> findByUserName(String userName);
    Optional<SiteUser> findByEmail(String email);

    boolean existsByUserName(String userName);
}
