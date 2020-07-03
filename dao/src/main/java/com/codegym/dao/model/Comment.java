package com.codegym.dao.model;

import com.codegym.dao.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "comment")
@JsonIgnoreProperties({
        "replies"
})
public class Comment extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    private String content;
    @Column(columnDefinition = "TINYINT(1) default 1")
    private boolean status = true;

    @OneToMany(
            mappedBy = "comment",
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL })
    private Set<Reply> replies;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "FK_comment_post"))
    private Post post;

    public Comment() {
    }

    public Comment(String content, boolean status) {
        this.content = content;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
