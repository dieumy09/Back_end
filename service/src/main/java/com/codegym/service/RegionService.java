package com.codegym.service;

import com.codegym.dao.model.Region;

import java.util.List;

public interface RegionService {
    List<Region> findAll();
    Region findById(Long id);
    void save(Region region);
    void deleteById(Long id);
}
