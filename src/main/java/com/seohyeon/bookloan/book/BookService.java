package com.seohyeon.bookloan.book;

import com.seohyeon.bookloan.book.Book;
import com.seohyeon.bookloan.dto.BookDto;
import com.seohyeon.bookloan.exception.DataNotFoundException;
import com.seohyeon.bookloan.book.BookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final int PAGE_SIZE = 8;

    public Book getBook(Long id) throws DataNotFoundException{
        var b = bookRepository.findById(id);
        if (b.isPresent())
            return b.get();
        throw new DataNotFoundException(id.toString(), Book.class);
    }

    public PagedModel<BookDto> getBooks(int page, String keyword){
        List<Sort.Order> sorts = List.of(Sort.Order.asc("createdDate"));
        var pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(sorts));
        var spec = titleLike(keyword);
        var res = bookRepository.findAll(spec, pageable).map(BookDto::of);

        return new PagedModel<>(res);
    }

    private Specification<Book> titleLike(String kw){
        return (b, query, cb) -> {
            query.distinct(true);
            // Join
            return cb.or(cb.like(b.get("title"), "%" + kw + "%"));
        };
    }
}
