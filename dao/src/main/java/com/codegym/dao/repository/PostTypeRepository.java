package com.codegym.dao.repository;

import com.codegym.dao.model.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTypeRepository extends JpaRepository<PostType, Long> {
}
