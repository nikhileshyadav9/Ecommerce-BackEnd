package com.ecommerce.utility;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	List<String> loadAll();
	
	String store(MultipartFile file);

    Resource load(String filename);

    void delete(String filename);
    
    void init();
    
}
