package com.codegym.service;

import com.codegym.dao.model.PostType;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface PostTypeService {
    List<PostType> findAll();
    PostType findById(Long id);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void save(PostType postType);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(Long id);
}
