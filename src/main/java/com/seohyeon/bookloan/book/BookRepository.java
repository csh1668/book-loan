package com.seohyeon.bookloan.book;

import com.seohyeon.bookloan.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Specification<Book> spec, Pageable pageable);
}
