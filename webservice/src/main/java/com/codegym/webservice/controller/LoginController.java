package com.codegym.webservice.controller;



import com.codegym.configuration.security.TokenJWTUtils;
import com.codegym.dao.DTO.JwtResponse;
import com.codegym.dao.DTO.UserDTO;
import com.codegym.service.serviceImpl.UserDetailsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping(path = "/api/v1/login")
public class LoginController {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired
    TokenJWTUtils tokenJWTUtils;
    @Autowired(required = false)
    UserDetailsServiceimpl userDetailsServiceimpl;

//    private UserDTO userDTO;
//    @GetMapping("/admin")
//    public ResponseEntity<?> helloAdmin() {
//        userDTO=new UserDTO("admin","Hello");
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }
//
//    @GetMapping("/user")
//    public ResponseEntity<?> helloUser() {
//        userDTO=new UserDTO("user","Hello");
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }


    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody UserDTO user){
        System.out.println(user.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        UserDetails userDetails = userDetailsServiceimpl
                .loadUserByUsername(authentication.getName());
        String jwtToken=tokenJWTUtils.generateToken(userDetails);
        return ResponseEntity.ok( new JwtResponse(jwtToken));
    }
}
