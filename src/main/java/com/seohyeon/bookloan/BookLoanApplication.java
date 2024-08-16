package com.seohyeon.bookloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class BookLoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookLoanApplication.class, args);
    }

}
