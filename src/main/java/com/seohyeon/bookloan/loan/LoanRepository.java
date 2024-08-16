package com.seohyeon.bookloan.loan;

import com.seohyeon.bookloan.book.Book;
import com.seohyeon.bookloan.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByLoaner_Id(Long loanerId);

    Optional<Loan> findByLoanerAndBookId(SiteUser loaner, Book bookId);
}
