package com.codegym.webservice.controller;

import com.codegym.dao.model.Post;
import com.codegym.dao.model.User;
import com.codegym.service.PostService;
import com.codegym.service.UserService;
import com.codegym.webservice.payload.ApiResponse;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private PostService postService;
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }


    //-------------------Get All Users--------------------------------------------------------

    @GetMapping()
    public ResponseEntity<Object> findAllUsers(Pageable pageable){
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    //-------------------Get One User By Id--------------------------------------------------------

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUserById(@PathVariable("id")Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(new ApiResponse(false, "Can not find user!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //-------------------Create a User--------------------------------------------------------

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location)
                .body(user);
    }

    //-------------------Update a User--------------------------------------------------------

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        if (userService.findById(id) == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Can not find this user!"), HttpStatus.NOT_FOUND);
        }
        userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location)
                .body(user);
    }

    //-------------------Delete a User--------------------------------------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(new ApiResponse(false, "Can not find this user!"), HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(true, "Delete user successfully!"), HttpStatus.OK);
    }

    //-------------------Find Posts By UserId Pagination And Search--------------------------------------------------------

    @GetMapping("/{id}/posts")
    public ResponseEntity<Object> getPostsByUserId(@PathVariable("id") Long id, @PageableDefault(size = 5) Pageable pageable, @RequestParam("search") String search) {
        Page<Post> posts = null;
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(new ApiResponse(false, "Can not find user!"), HttpStatus.NOT_FOUND);
        }
        if (search != null) {
            posts = postService.findPostsByUser_IdAndTitleContaining(id, search, pageable);
        }
        else {
            posts = postService.findPostsByUserId(id, pageable);
        }
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }


}
