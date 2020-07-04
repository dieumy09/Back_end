package com.codegym.service;

import com.codegym.dao.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface PostService {
    Page<Post> findAll(Pageable pageable);
    Post findById(Long id);
    void save(Post post);
    void deleteById(Long id);
    Page<Post> findAllBySearchModal(
            Pageable pageable,
            Long categoryId,
            Long regionId,
            Long postTypeId,
            Boolean condition,
            Double area,
            Long price,
            Boolean deal,
            Long directionId,
            String keyword
    );
}
