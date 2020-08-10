package com.codegym.service.serviceImpl;

<<<<<<< HEAD
=======

import com.codegym.dao.model.ERole;
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
import com.codegym.dao.model.Role;
import com.codegym.dao.model.User;
import com.codegym.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD
@Service
public class UserDetailsServiceimpl implements UserDetailsService {

=======

@Service
public class UserDetailsServiceimpl implements UserDetailsService {


>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
<<<<<<< HEAD
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
=======
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
//
//        return UserDetailsImpl.build(user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for(Role role: roles){
<<<<<<< HEAD
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                grantedAuthorities);
    }
}
=======
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName().toString()));
        }
        return UserDetailsImpl.build(user);
    }
}


>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
