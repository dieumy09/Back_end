package com.codegym.service;

<<<<<<< HEAD
import com.codegym.dao.DTO.UserDTO;
=======
import com.codegym.dao.model.AccountReport;
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605
import com.codegym.dao.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605

public interface UserService {
    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

<<<<<<< HEAD
=======
    void blockById(Long id, AccountReport accountReport);

    void unblockById(Long id);

    Page<User> searchUser(Pageable pageable, String keyword);

>>>>>>> f8cedb3e9d3334f882c48c1589566ed0a7797605

}
