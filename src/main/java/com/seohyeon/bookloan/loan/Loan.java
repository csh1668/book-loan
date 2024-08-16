package com.seohyeon.bookloan.loan;

import com.seohyeon.bookloan.book.Book;
import com.seohyeon.bookloan.entity.BaseTimeEntity;
import com.seohyeon.bookloan.user.SiteUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Loan extends BaseTimeEntity {
    private final static int LOAN_PERIOD = 7;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private SiteUser loaner;
    @ManyToOne
    private Book bookId;

    private LocalDateTime loanDate;
    private LocalDateTime returnDate;

    @Builder
    public Loan(SiteUser loaner, Book bookId){
        this.loaner = loaner;
        this.bookId = bookId;
        this.loanDate = LocalDateTime.now();
        this.returnDate = LocalDateTime.now().plusDays(LOAN_PERIOD);
    }
}
