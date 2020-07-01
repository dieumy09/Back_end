package com.codegym.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "post_image")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @ManyToOne()
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public PostImage() {
    }

    public PostImage(String image, Post post) {
        this.image = image;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
