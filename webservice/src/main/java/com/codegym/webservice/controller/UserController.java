package com.codegym.webservice.controller;

import com.codegym.dao.model.AccountReport;
import com.codegym.dao.model.Post;
import com.codegym.dao.model.User;
import com.codegym.service.PostService;
import com.codegym.service.UserService;
import com.codegym.webservice.payload.ApiResponse;
import com.codegym.webservice.payload.BlockUserRequest;
import com.codegym.webservice.payload.ChangePasswordToken;
import com.codegym.webservice.payload.UserSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * A controller used to perform CRUD and manage user of the system
 * @Author Rhys
 */
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

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    private final String NOT_FOUND_USER = "Cannot find this user!";



    /**
     * @param pageable containing pagination information
     * @return A list of all users in system
     */
    @GetMapping()
    public ResponseEntity<Object> findAllUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    /**
     * @param id the id of the user needs to be found
     * @return found user information
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * @param user information of an user
     * @return
     */
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


    /**
     * @param id the id of the user need to be updated
     * @param user the variable contains data that has bean updated
     * @return the user that has bean updated or a message if can not find user
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
        User currentUser = userService.findById(id);

        if (currentUser == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }

        currentUser.setAvatar(user.getAvatar());
        currentUser.setName(user.getName());
        currentUser.setAddress(user.getAddress());
        currentUser.setPhoneNumber(user.getPhoneNumber());

        userService.save(currentUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(currentUser.getId()).toUri();
        return ResponseEntity.created(location)
                .body(currentUser);
    }

    /**
     * @param id the id of the user need to be changed password
     * @param changePasswordToken the object contains current password and new password of the user
     * @return the current user information
     */
    @PatchMapping("/{id}/changePassword")
    public ResponseEntity<Object> changePassword(@PathVariable Long id, @RequestBody ChangePasswordToken changePasswordToken) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }

        if (!encoder.matches(changePasswordToken.getCurrentPassword(), user.getPassword())) {
            return new ResponseEntity<>(new ApiResponse(false, "The password is incorrect!"), HttpStatus.BAD_REQUEST);
        }
        user.setPassword(encoder.encode(changePasswordToken.getNewPassword()));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    /**
     * @param id the id of the user deleted
     * @return information of the user that has been deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(true, "Delete user successfully!"), HttpStatus.OK);
    }

    /**
     * @param id
     * @param pageable
     * @param search
     * @return
     */
    @GetMapping("/{id}/posts")
    public ResponseEntity<Object> getPostsByUserId(@PathVariable("id") Long id, @PageableDefault(size = 5) Pageable pageable, @RequestParam("search") String search) {
        Page<Post> posts;
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        if (search != null) {
            posts = postService.findPostsByUser_IdAndTitleContaining(id, search, pageable);
        } else {
            posts = postService.findPostsByUserId(id, pageable);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    /**
     * @param id
     * @param blockUserRequest
     * @return
     */
    @PostMapping("/{id}/block")
    public ResponseEntity<Object> blockUserById(@PathVariable Long id, @RequestBody BlockUserRequest blockUserRequest) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        AccountReport accountReport = new AccountReport();
        accountReport.setUser(user);
        accountReport.setReason(blockUserRequest.getReason());
        userService.blockById(id, accountReport);
        return ResponseEntity.ok().body(new ApiResponse(true, "Block account successfully!"));
    }

    /**
     * @param id
     * @return
     */
    @PostMapping("/{id}/unblock")
    public ResponseEntity<Object> unblockUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        userService.unblockById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Unblock account successfully!"));
    }


    /**
     * @param userSearchRequest
     * @param pageable
     * @return
     */
    @PostMapping("/search")
    public ResponseEntity<Object> searchUser(@RequestBody UserSearchRequest userSearchRequest, @PageableDefault() Pageable pageable) {
        return new ResponseEntity<>(userService.searchUser(pageable, userSearchRequest.getKeyword()), HttpStatus.OK);
    }
}
