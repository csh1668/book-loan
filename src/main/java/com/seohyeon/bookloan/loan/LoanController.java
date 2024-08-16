package com.seohyeon.bookloan.loan;

import com.seohyeon.bookloan.dto.LoanDto;
import com.seohyeon.bookloan.dto.ResponseDto;
import com.seohyeon.bookloan.user.SiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoanController {
    private final LoanService loanService;
    private final SiteUserService siteUserService;

    @GetMapping("/loan/{id}")
    public ResponseDto<?> loanBook(@PathVariable("id") Long id){
        try {
            var username = siteUserService.getMyInfoBySecurity().getUserName();
            var dto = LoanDto.builder()
                    .bookId(id)
                    .loaner(username)
                    .build();
            return loanService.loanBook(dto);
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

    @GetMapping("/loan/check/{id}")
    public ResponseDto<?> checkLoan(@PathVariable("id") Long id){
        try {
            var username = siteUserService.getMyInfoBySecurity().getUserName();
            var dto = LoanDto.builder()
                    .bookId(id)
                    .loaner(username)
                    .build();
            return loanService.checkLoan(dto);
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

    @GetMapping("/loan/list")
    public ResponseDto<?> loans(){
        try {
            var username = siteUserService.getMyInfoBySecurity().getUserName();
            return loanService.loans(LoanDto.builder().loaner(username).build());
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

    @GetMapping("/return/{id}")
    public ResponseDto<?> returnBook(@PathVariable("id") Long id){
        var username = siteUserService.getMyInfoBySecurity().getUserName();
        var dto = LoanDto.builder()
                .bookId(id)
                .loaner(username)
                .build();
        try {
            return loanService.returnBook(dto);
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }
}
