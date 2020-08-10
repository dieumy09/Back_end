package com.codegym.dao.repository;

import com.codegym.dao.model.ERole;
import com.codegym.dao.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
<<<<<<< HEAD
    Role findByRole(String role);
=======
    Optional<Role> findByRoleName(ERole name);

    Role findByRoleNameIs(ERole user);
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
}
