package com.codegym.service;

import com.codegym.dao.model.AccountReport;
import com.codegym.dao.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    void blockById(Long id, AccountReport accountReport);

    void unblockById(Long id);

    Page<User> searchUser(Pageable pageable, String keyword);
}
