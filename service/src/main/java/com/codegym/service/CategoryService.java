package com.codegym.service;


import com.codegym.dao.model.Category;

import java.util.List;

public interface CategoryService {
    void save(Category category);
    List<Category> findAll();
    Category findById(Long id);
    void deleteById(Long id);
}
