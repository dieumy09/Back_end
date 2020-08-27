package com.codegym.service;

import com.codegym.dao.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RegionService {
    List<Region> findAll();

    Page<Region> findAll(Pageable pageable);

    Region findById(Long id);

    void save(Region region);

    void deleteById(Long id);
}
