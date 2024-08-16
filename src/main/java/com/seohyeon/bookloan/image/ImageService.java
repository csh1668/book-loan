package com.seohyeon.bookloan.image;

import com.seohyeon.bookloan.dto.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${service.image-path}")
    private String imageRootPath;

    private final ImageRepository imageUploadRepository;

    public ImageUploadDto uploadImage(MultipartFile file) throws IOException {
        String orig = file.getOriginalFilename();
        if (orig.isEmpty()) orig = file.getName();
        String newFileName = createFileName(orig);
        String fullPath = getFullPath(newFileName);
        if (!new File(imageRootPath).exists())
            new File(imageRootPath).mkdirs();
        file.transferTo(new File(fullPath));
        imageUploadRepository.save(new Image(orig, newFileName));

        return new ImageUploadDto(file, newFileName);
    }

    public Image getImage(String fileName){
        var img = imageUploadRepository.findByOrigFileName(fileName);
        return img.orElse(null);
    }

    public byte[] downloadImage(Image img) throws IOException {
        var path = getFullPath(img.getFileName());
        var file = Paths.get(path);
        if (Files.exists(file)){
            return Files.readAllBytes(file);
        } else {
            throw new IOException("파일을 찾을 수 없습니다.");
        }
    }

    private String createFileName(String orig){
        String ext = extractExt(orig);
        if (ext.isEmpty()) ext = "png";
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String orig){
        int pos = orig.lastIndexOf(".");
        return orig.substring(pos + 1);
    }

    private String getFullPath(String path){
        return imageRootPath + path;
    }
}
