package com.codegym.service.serviceImpl;

import com.codegym.dao.model.PostType;
import com.codegym.dao.repository.PostTypeRepository;
import com.codegym.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTypeServiceImpl implements PostTypeService {
    private PostTypeRepository postTypeRepository;

    @Autowired
    public void setPostTypeRepository(PostTypeRepository postTypeRepository) {
        this.postTypeRepository = postTypeRepository;
    }

    @Override
    public List<PostType> findAll() {
        return postTypeRepository.findAll();
    }

    @Override
    public PostType findById(Long id) {
        return postTypeRepository.findById(id).orElse(null);
    }

    @Override
    public void save(PostType postType) {
        postTypeRepository.save(postType);
    }

    @Override
    public void deleteById(Long id) {
        postTypeRepository.deleteById(id);
    }
}
