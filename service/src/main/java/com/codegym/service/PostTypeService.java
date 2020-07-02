package com.codegym.service;

import com.codegym.dao.model.PostType;

import java.util.List;

public interface PostTypeService {
    List<PostType> findAll();
    PostType findById(Long id);
    void save(PostType postType);
    void deleteById(Long id);
}
