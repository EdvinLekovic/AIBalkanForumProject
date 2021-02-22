package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image store(MultipartFile multipartFile) throws IOException;
    Image findImageById(Long id);
    List<Image> findAll();
}
