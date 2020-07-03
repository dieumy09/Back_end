package com.codegym.dao.model;

import com.codegym.dao.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "post_image")
public class PostImage extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NVARCHAR(50)", nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(name = "post_id", nullable = false, foreignKey = @ForeignKey(name = "FK_post_image_post"))
    @JsonBackReference
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
