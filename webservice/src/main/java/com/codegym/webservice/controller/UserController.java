package com.codegym.webservice.controller;

import com.codegym.dao.model.User;
import com.codegym.service.UserService;
import com.codegym.webservice.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            return new ResponseEntity<>(new ApiResponse(false, "Can not find user!"), HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<>(new ApiResponse(false, "Can not find user!"), HttpStatus.BAD_REQUEST);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
