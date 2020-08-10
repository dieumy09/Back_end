package com.codegym.dao.repository;

import com.codegym.dao.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
//    Page<Post> findAllBy
    Page<Post> getPostsByUser_Id(Long userId, Pageable pageable);
    Page<Post> getPostsByUser_IdAndTitleContaining(Long userId, String title, Pageable pageable);
}
