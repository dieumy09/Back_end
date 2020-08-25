package com.codegym.webservice.controller;

import com.codegym.dao.model.Post;
import com.codegym.dao.model.User;
import com.codegym.service.PostService;
import com.codegym.service.UserService;
import com.codegym.webservice.payload.ApiResponse;
import com.codegym.webservice.payload.PostSearchByYearRequest;
import com.codegym.webservice.payload.PostSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {
    private PostService postService;
    private UserService userService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //-------------------Get All Posts--------------------------------------------------------
    @GetMapping
    public ResponseEntity<Object> findAllPosts(Pageable pageable) {
        return new ResponseEntity<>(postService.findAll(pageable), HttpStatus.OK);
    }

    //-------------------Get Posts By UserId--------------------------------------------------------
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<Object> findPostsByUserId(@PageableDefault(value = 5) Pageable pageable, @PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Can not find user!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postService.findPostsByUserId(userId, pageable), HttpStatus.OK);
    }

    //-------------------Get One Post By Id--------------------------------------------------------
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findPostById(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Can not find this post!"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
    }

    //-------------------Create a Post--------------------------------------------------------
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

    //-------------------Update a Post by id--------------------------------------------------------
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id, @Valid @RequestBody Post post) {
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

    //-------------------Delete a Post by id--------------------------------------------------------
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
    public ResponseEntity<Object> findAllBySearchModal(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestBody PostSearchRequest postSearchRequest) {
        String direction = "";
        int size = 9;
        Sort sortable = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sortable);
        if (postSearchRequest.getYear() != null) {
            int year = Integer.parseInt(postSearchRequest.getYear());
            if (((year % 4 == 0) && (year % 100 != 0)) ||
                    (year % 400 == 0)) {
                if (year % 2 == 0) {
                    direction += "Tây ";
                } else {
                    direction += "Đông ";
                }
                if (postSearchRequest.getGender()) {
                    direction += "Nam ";
                } else  {
                    direction += "Bắc ";
                }
            } else {
                if (year % 2 == 0 && postSearchRequest.getGender() == true) {
                    direction += "Nam ";
                }
                if (year % 2 == 0 && postSearchRequest.getGender() == false) {
                    direction += "Bắc ";
                }
                if (year % 2 != 0 && postSearchRequest.getGender() == true) {
                    direction += "Tây ";
                }
                if (year % 2 != 0 && postSearchRequest.getGender() != false) {
                    direction += "Đông ";
                }
            }
        }
        Page<Post> posts = postService.findAllBySearchModal(
                pageable, postSearchRequest.getCategoryId(),
                postSearchRequest.getRegionId(),
                postSearchRequest.getPostTypeId(),
                postSearchRequest.getCondition(),
                postSearchRequest.getArea(),
                postSearchRequest.getPrice(),
                postSearchRequest.getDeal(),
                postSearchRequest.getDirectionId(),
                postSearchRequest.getKeyword(),
                postSearchRequest.getCustomerType(),
                direction
        );
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //-------------------Get top 10 Post By ViewCount--------------------------------------------------------
    @GetMapping(value = "/mostViewCount")
    public ResponseEntity<Object> findByViewCount() {
        return new ResponseEntity<>(postService.findByViewCount(), HttpStatus.OK);
    }
}
