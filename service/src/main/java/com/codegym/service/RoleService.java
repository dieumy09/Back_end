package com.codegym.service;

import com.codegym.dao.model.Role;

public interface RoleService {
    Iterable<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void deleteById(Long id);
}
