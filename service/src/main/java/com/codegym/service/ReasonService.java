package com.codegym.service;

import com.codegym.dao.model.Reason;

import java.util.List;

public interface ReasonService {
    void save(Reason reason);
    List<Reason> findAll();
    Reason findById(Long id);
    void delete(Long id);
}
