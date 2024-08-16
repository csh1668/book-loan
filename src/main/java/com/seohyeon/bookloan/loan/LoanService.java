package com.seohyeon.bookloan.loan;

import com.seohyeon.bookloan.book.BookRepository;
import com.seohyeon.bookloan.dto.LoanDto;
import com.seohyeon.bookloan.dto.ResponseDto;
import com.seohyeon.bookloan.user.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final SiteUserRepository siteUserRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public ResponseDto<?> loanBook(LoanDto loanDto){
        var user = siteUserRepository.findByUserName(loanDto.getLoaner());
        System.out.println("loanBook" + user);
        if (user.isEmpty()) {
            return ResponseDto.error("User not found");
        }
        var book = bookRepository.findById(loanDto.getBookId());
        if (book.isEmpty()) {
            return ResponseDto.error("Book not found");
        }

        var curLoans = user.get().getLoans();
        if (curLoans.size() >= 3) {
            return ResponseDto.error("User has reached the maximum number of loans");
        }
        if (curLoans.stream().anyMatch(loan -> loan.getBookId().getId().equals(loanDto.getBookId()))) {
            return ResponseDto.error("User has already loaned this book");
        }


        var loan = Loan.builder()
                .loaner(user.get())
                .bookId(book.get())
                .build();
        user.get().getLoans().add(loan);
        siteUserRepository.save(user.get());
        return ResponseDto.ok(LoanDto.of(loanRepository.save(loan)));
    }

    public ResponseDto<?> loans(LoanDto loanDto){
        var user = siteUserRepository.findByUserName(loanDto.getLoaner());
        if (user.isEmpty()) {
            return ResponseDto.error("User not found");
        }
        return ResponseDto.ok(user.get().getLoans().stream().map(LoanDto::of).collect(Collectors.toList()));
    }

    public ResponseDto<?> returnBook(LoanDto loanDto){
        var user = siteUserRepository.findByUserName(loanDto.getLoaner());
        if (user.isEmpty()) {
            return ResponseDto.error("User not found");
        }
        var book = bookRepository.findById(loanDto.getBookId());
        if (book.isEmpty()) {
            return ResponseDto.error("Book not found");
        }

        var loan = loanRepository.findByLoanerAndBookId(user.get(), book.get());
        if (loan.isEmpty()) {
            return ResponseDto.error("User has not loaned this book");
        }

        user.get().getLoans().remove(loan.get());
        siteUserRepository.save(user.get());
        loanRepository.delete(loan.get());
        return ResponseDto.ok(LoanDto.of(loan.get()));
    }

    public ResponseDto<?> checkLoan(LoanDto loanDto) {
        var user = siteUserRepository.findByUserName(loanDto.getLoaner());
        if (user.isEmpty()) {
            return ResponseDto.error("User not found");
        }
        var book = bookRepository.findById(loanDto.getBookId());
        if (book.isEmpty()) {
            return ResponseDto.error("Book not found");
        }

        var loan = loanRepository.findByLoanerAndBookId(user.get(), book.get());
        if (loan.isEmpty()) {
            return ResponseDto.error("User has not loaned this book");
        }
        return ResponseDto.ok(LoanDto.of(loan.get()));
    }
}
