package com.seohyeon.bookloan.dto;

import com.seohyeon.bookloan.loan.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDto {
    private String loaner;
    private Long bookId;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;

    public static LoanDto of(Loan loan){
        return LoanDto.builder()
                .loaner(loan.getLoaner().getUserName())
                .bookId(loan.getBookId().getId())
                .loanDate(loan.getLoanDate())
                .returnDate(loan.getReturnDate())
                .build();
    }
}
