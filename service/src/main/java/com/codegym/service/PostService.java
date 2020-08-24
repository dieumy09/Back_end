package com.codegym.service;

import com.codegym.dao.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface PostService {
    Page<Post> findAll(Pageable pageable);
    Post findById(Long id);
    @PreAuthorize("hasRole('ROLE_USER')")
    void save(Post post);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(Long id);
    Page<Post> findPostsByUserId(Long userId, Pageable pageable);
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
            String keyword,
            Boolean customerType,
            String direction
    );
    @PreAuthorize("hasRole('ROLE_USER')")
    Page<Post> findPostsByUser_IdAndTitleContaining(Long userId, String title, Pageable pageable);

    Page<Post> findPendingPosts(String keyword, Pageable pageable);

    Page<Post> searchApprovedPosts(String keyword, Pageable pageable);
}
