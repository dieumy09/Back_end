package com.codegym.service;

import com.codegym.dao.model.PostImage;

import java.util.List;
import java.util.Set;

public interface PostImageService {
    List<PostImage> findAll();
    PostImage findById(Long id);
    Set<PostImage> findByPostId(Long id);
    void save(PostImage postImage);
    void deleteById(Long id);
}
