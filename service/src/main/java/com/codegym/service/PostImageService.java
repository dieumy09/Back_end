package com.codegym.service;

import com.codegym.dao.model.PostImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PostImageService {
    Page<PostImage> findAll(Pageable pageable);
    PostImage findById(Long id);
    Set<PostImage> findByPostId(Long id);
    void save(PostImage postImage);
    void deleteById(Long id);
}
