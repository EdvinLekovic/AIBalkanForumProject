package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.Image;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidImageIdException;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidImageNameException;
import com.webproject.aibalkanforumproject.repository.ImageRepository;
import com.webproject.aibalkanforumproject.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Image image = new Image(fileName,file.getContentType(),file.getBytes());

        return imageRepository.save(image);
    }

    @Override
    public Image findImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new InvalidImageIdException(id));
    }

    public Image findImageByName(String name){
        return imageRepository.findImageByName(name).orElseThrow(() -> new InvalidImageNameException(name));
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }
}
