package com.codegym.service;

import com.codegym.dao.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<Post> findAll(Pageable pageable);
    Post findById(Long id);
    void save(Post post);
    void deleteById(Long id);
}
