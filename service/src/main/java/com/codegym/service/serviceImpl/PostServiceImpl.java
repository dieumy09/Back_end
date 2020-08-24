package com.codegym.service.serviceImpl;

import com.codegym.dao.model.Post;
import com.codegym.dao.repository.PostRepository;
import com.codegym.service.PostService;
import com.codegym.service.specification.PostSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<Post> findAllBySearchModal(Pageable pageable, Long categoryId, Long regionId, Long postTypeId, Boolean condition, Double area, Long price, Boolean deal, Long directionId, String keyword, Boolean customerType, String direction) {
        return postRepository.findAll(
                Specification
                        .where(PostSpecification.hasCategoryId(categoryId))
                        .and(PostSpecification.hasRegionId(regionId))
                        .and(PostSpecification.hasPostTypeId(postTypeId))
                        .and(PostSpecification.hasCondition(condition))
                        .and(PostSpecification.hasArea(area))
                        .and(PostSpecification.hasPrice(price))
                        .and(PostSpecification.hasDeal(deal))
                        .and(PostSpecification.hasDirectionId(directionId))
                        .and(PostSpecification.isApproved(true))
                        .and(PostSpecification.hasCustomerType(customerType))
                        .and(PostSpecification.textInAllColumns(keyword, Arrays.asList("title", "content")))
                        .and(PostSpecification.hasDirection(direction))
                , pageable);
    }

    @Override
    public Page<Post> findPostsByUserId(Long userId, Pageable pageable) {
        return postRepository.getPostsByUser_Id(userId, pageable);
    }

    @Override
    public Page<Post> findPostsByUser_IdAndTitleContaining(Long userId, String title, Pageable pageable) {
        return postRepository.getPostsByUser_IdAndTitleContaining(userId, title, pageable);
    }

    @Override
    public Page<Post> findPendingPosts(String keyword, Pageable pageable) {
        return postRepository.findAll(
                Specification.where(PostSpecification.isApproved(false))
                .and(PostSpecification.textInAllColumns(keyword, Arrays.asList("title", "content")))
                , pageable);
    }

    @Override
    public Page<Post> searchApprovedPosts(String keyword, Pageable pageable) {
        return postRepository.findAll(
                Specification.where(PostSpecification.isApproved(true))
                        .and(PostSpecification.textInAllColumns(keyword, Arrays.asList("title", "content")))
                , pageable);
    }

}
