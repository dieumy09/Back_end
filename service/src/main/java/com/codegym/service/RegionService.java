package com.codegym.service;

import com.codegym.dao.model.Region;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface RegionService {
    List<Region> findAll();
    Region findById(Long id);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void save(Region region);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(Long id);
}
