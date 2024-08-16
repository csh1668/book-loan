package com.seohyeon.bookloan.book_category;

import com.seohyeon.bookloan.book.Book;
import com.seohyeon.bookloan.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BookCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;
}
