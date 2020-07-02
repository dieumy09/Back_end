package com.codegym.service;

import com.codegym.dao.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    void save(Comment comment);
    Page<Comment> findAll(Pageable pageable);
    Comment findById(Long id);
    void delete(Long id);
}
