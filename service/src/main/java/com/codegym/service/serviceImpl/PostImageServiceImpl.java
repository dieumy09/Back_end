package com.codegym.service.serviceImpl;

import com.codegym.dao.model.Post;
import com.codegym.dao.model.PostImage;
import com.codegym.dao.repository.PostImageRepository;
import com.codegym.service.PostImageService;
import com.codegym.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PostImageServiceImpl implements PostImageService {
    private PostImageRepository postImageRepository;

    @Autowired
    public void setPostImageRepository(PostImageRepository postImageRepository) {
        this.postImageRepository = postImageRepository;
    }

    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Override
    public Page<PostImage> findAll(Pageable pageable) {
        return postImageRepository.findAll(pageable);
    }

    @Override
    public PostImage findById(Long id) {
        return postImageRepository.findById(id).orElse(null);
    }

    @Override
    public Set<PostImage> findByPost(Long id) {
        Post post = postService.findById(id);
        return post.getPostImages();
    }

    @Override
    public void save(PostImage postImage) {
        postImageRepository.save(postImage);
    }

    @Override
    public void deleteById(Long id) {
        postImageRepository.deleteById(id);
    }
}
