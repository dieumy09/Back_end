package com.codegym.webservice.controller;

import com.codegym.dao.model.Category;
import com.codegym.service.CategoryService;
import com.codegym.webservice.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Object> findAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findRepositoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Can not find category!"), HttpStatus.BAD_REQUEST);
        } else  {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        categoryService.save(category);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(location)
                .body(category);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(location)
                .body(category);
    }


}
