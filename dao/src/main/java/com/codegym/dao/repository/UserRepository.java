package com.codegym.dao.repository;

import com.codegym.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

<<<<<<< HEAD
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
=======
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByEmail(String email);

    Boolean existsByEmail(String email);
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
}
