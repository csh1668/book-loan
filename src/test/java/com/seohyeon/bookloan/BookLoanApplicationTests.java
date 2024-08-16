package com.seohyeon.bookloan;

import com.seohyeon.bookloan.book.Book;
import com.seohyeon.bookloan.book.BookRepository;
import com.seohyeon.bookloan.image.ImageService;
import com.seohyeon.bookloan.jwt.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
class BookLoanApplicationTests {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private ImageService imageUploadService;

    @Test
    void addBook() throws IOException {
//        var b = new Book("나는 나로 살기로 했다", "빅데이터로도 증명된 ‘나로 살기’ 열풍을 이끌며 시대정신을 만든 책. 2016년 출간 이후 국내에서만 100만 부 이상 판매되며 전국 서점 260주 연속 베스트셀러에 오른 책.", "김수현");
//        bookRepository.save(b);
//        var path = "C:\\Users\\조서현\\Downloads\\7.jpg";
//        var file = new MockMultipartFile(7 + ".jpg", new FileInputStream(path));
//
//        System.out.println(file.getBytes().length);
//        imageUploadService.uploadImage(file);
    }
    @Test
    void bookTest() {
//        Book b1 = new Book("한권으로 끝내기 JLPT N2", "일본어 능력 시험 공부", "이치우");
//        bookRepository.save(b1);
//        Book b2 = new Book("자이스토리 고3 미적분", "수능 완전 대비!", "윤혜미");
//        bookRepository.save(b2);
//        Book b3 = new Book("가끔씩 툭하고 러시아러로 부끄러워하는 옆자리의 아랴 양", "전교생이 동경하는 초 하이스펙 러시안 여고생과의 청춘 러브 코미디!", "SUN SUN SUN");
//        bookRepository.save(b3);
    }

    @Test
    void ImageUploadTest() throws IOException {
//        var path = "C:\\Users\\조서현\\Downloads\\";
//        for (int i = 1; i <= 5; ++i){
//            var cur = path + i + ".jpg";
//            var file = new MockMultipartFile(i + ".jpg", new FileInputStream(cur));
//
//            System.out.println(file.getBytes().length);
//            imageUploadService.uploadImage(file);
//        }
    }

}
