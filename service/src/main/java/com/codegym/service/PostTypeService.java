package com.codegym.service;

import com.codegym.dao.model.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostTypeService {
    List<PostType> findAll();

    Page<PostType> findAll(Pageable pageable);

    PostType findById(Long id);

    void save(PostType postType);

    void deleteById(Long id);
}
