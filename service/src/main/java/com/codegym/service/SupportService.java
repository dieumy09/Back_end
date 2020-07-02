package com.codegym.service;

import com.codegym.dao.model.Support;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupportService {
    Page<Support> findAll(Pageable pageable);

    Support findById(Long id);

    void save(Support support);

    void deleteById(Long id);
}
