package com.seohyeon.bookloan.book;

import com.seohyeon.bookloan.book_category.BookCategory;
import com.seohyeon.bookloan.dto.BookDto;
import com.seohyeon.bookloan.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private String author;

    @ManyToMany
    private List<BookCategory> categories;

    @Builder
    public Book(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
    return "Book{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", author='" + author + '\'' +
            ", createdDate=" + getCreatedDate() +
            '}';
    }

    public static Book of(BookDto dto){
        return Book.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .author(dto.getAuthor())
                .build();
    }
}
