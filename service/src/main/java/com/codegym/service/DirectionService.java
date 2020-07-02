package com.codegym.service;

import com.codegym.dao.model.Direction;

import java.util.List;

public interface DirectionService {
    void save(Direction direction);
    List<Direction> findAll();
    Direction findById(Long id);
    void deleteById(Long id);
}
