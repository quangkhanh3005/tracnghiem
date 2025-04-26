package com.example.tracnghiem.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {
    private final Path UPLOAD_DIR = Paths.get(System.getProperty("user.dir"), "upload");
    @PostConstruct
    public void init() {
        if (!Files.exists(UPLOAD_DIR)) {
            try {
                Files.createDirectories(UPLOAD_DIR);
            } catch (IOException e) {
                throw new RuntimeException("Không thể tạo thư mục uploads");
            }
        }
    }
    public String uploadImage(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
            Path filePath=UPLOAD_DIR.resolve(fileName);
            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu ảnh", e);
        }
    }
    public void deleteImage(String imagePath) {
        File file = new File("upload/" + imagePath);
        if (file.exists()) {
            file.delete();
        }
    }

}
