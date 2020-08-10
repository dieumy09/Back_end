package com.codegym.service;

import com.codegym.dao.DTO.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String userName);
    List<UserDTO> findAll();
}
