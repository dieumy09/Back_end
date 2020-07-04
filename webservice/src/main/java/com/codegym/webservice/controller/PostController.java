package com.codegym.webservice.controller;

import com.codegym.dao.model.Post;
import com.codegym.service.PostService;
import com.codegym.webservice.payload.ApiResponse;
import com.codegym.webservice.payload.PostSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<Object> findAllPosts(Pageable pageable) {
        return new ResponseEntity<>(postService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findPostById(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Can not find this post!"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody Post post) {
        postService.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location)
                .body(post);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        if (postService.findById(id) == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Can not find this post!"), HttpStatus.NOT_FOUND);
        }
        postService.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location)
                .body(post);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post == null){
            return new ResponseEntity<>(new ApiResponse(false, "Can not find post!"), HttpStatus.NOT_FOUND);
        } else {
            postService.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(true, "Delete post successfully!"), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/searchAll")
    public ResponseEntity<Object> findAllBySearchModal(Pageable pageable, @RequestBody PostSearchRequest postSearchRequest) {
        Page<Post> posts = postService.findAllBySearchModal(
                pageable, postSearchRequest.getCategoryId(),
                postSearchRequest.getRegionId(),
                postSearchRequest.getPostTypeId(),
                postSearchRequest.getCondition(),
                postSearchRequest.getArea(),
                postSearchRequest.getPrice(),
                postSearchRequest.getDeal(),
                postSearchRequest.getDirectionId(),
                postSearchRequest.getKeyword()
        );
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }



}
