package com.seohyeon.bookloan.dto;

import com.seohyeon.bookloan.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private String author;

    public static BookDto of(Book b){
        return BookDto.builder()
                .id(b.getId())
                .title(b.getTitle())
                .description(b.getDescription())
                .author(b.getAuthor())
                .build();
    }
}
