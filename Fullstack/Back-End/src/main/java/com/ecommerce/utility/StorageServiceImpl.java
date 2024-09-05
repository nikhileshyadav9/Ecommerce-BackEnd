package com.ecommerce.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class StorageServiceImpl implements StorageService {

	@Value("${disk.upload.basepath}")
    private String basePath;
	
	@Override
    public List<String> loadAll() {
        File dir = new File(basePath);
        if (dir.exists()) {
            return Arrays.asList(dir.list());
        } else {
            throw new RuntimeException("Directory not found: " + basePath);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ext;
        File filePath = new File(basePath, fileName);

        try (FileOutputStream out = new FileOutputStream(filePath)) {
            FileCopyUtils.copy(file.getInputStream(), out);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public Resource load(String filename) {
        File filePath = new File(basePath, filename);
        if (filePath.exists()) {
            return new FileSystemResource(filePath);
        } else {
            throw new RuntimeException("File not found: " + filename);
        }
    }

    @Override
    public void delete(String filename) {
        File filePath = new File(basePath, filename);
        if (filePath.exists()) {
            boolean deleted = filePath.delete();
            if (!deleted) {
                throw new RuntimeException("Failed to delete file: " + filename);
            }
        } else {
            throw new RuntimeException("File not found: " + filename);
        }
    }

    @Override
    public void init() {
        File dir = new File(basePath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create directory: " + basePath);
            }
        }
    }

}
