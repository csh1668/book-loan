package com.seohyeon.bookloan.book;


import com.seohyeon.bookloan.dto.ResponseDto;
import com.seohyeon.bookloan.exception.DataNotFoundException;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @GetMapping("/test")
    public String test() {
        return "Spring에서 보내는 데이터";
    }

    @GetMapping("/get/{id}")
    public ResponseDto<?> getBook(@PathVariable("id") Long id) {
        try {
            Book b = bookService.getBook(id);
            return ResponseDto.ok(b);
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseDto.error(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseDto<?> getBooks(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "") String kw) {
        return ResponseDto.ok(bookService.getBooks(page, kw));
    }
}
