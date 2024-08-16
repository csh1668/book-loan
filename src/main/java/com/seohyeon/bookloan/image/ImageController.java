package com.seohyeon.bookloan.image;

import com.seohyeon.bookloan.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageController {
    private final ImageService imageUploadService;

    @PostMapping("/upload")
    public ResponseDto<?> uploadImage(@RequestPart("image") MultipartFile image){
//        try {
//            return ResponseDto.ok(imageUploadService.uploadImage(image));
//        } catch (Exception e) {
//            return ResponseDto.error(e.getMessage());
//        }
        return null;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("fileName") String filename) {
        var img = imageUploadService.getImage(filename);
        if (img == null)
            return ResponseEntity.notFound().build();
        try {
            var headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(imageUploadService.downloadImage(img));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
