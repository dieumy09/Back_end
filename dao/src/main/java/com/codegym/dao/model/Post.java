package com.codegym.dao.model;

import com.codegym.dao.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "post")
@JsonIgnoreProperties({
        "comments"
})
public class Post extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NVARCHAR(50)")
    private String title;

    @Column(name = "property_condition")
    private boolean condition;

    @Column(columnDefinition = "NVARCHAR(100)")
    private String address;

    private Double area;

    private Long price;

    @Column(columnDefinition = "BIT(1) default 1")
    private boolean deal;

    private Long viewCount;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "BIT(1) default 1")
    private boolean status;

    @Column(columnDefinition = "BIT(1) default 0")
    private boolean approved;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
    })
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_post_user"))
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "post_type_id", foreignKey = @ForeignKey(name = "FK_post_post_type"))
    private PostType postType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "region_id", foreignKey = @ForeignKey(name = "FK_post_region"))
    private Region region;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "direction_id", foreignKey = @ForeignKey(name = "FK_post_direction"))
    private Direction direction;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_post_category"))
    private Category category;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<PostImage> postImages;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    public Post() {
    }

    public Post(String title, boolean condition, String address, Double area, Long price, boolean deal, Long viewCount, String content, boolean status, boolean approved, User user, PostType postType, Region region, Direction direction, Category category) {
        this.title = title;
        this.condition = condition;
        this.address = address;
        this.area = area;
        this.price = price;
        this.deal = deal;
        this.viewCount = viewCount;
        this.content = content;
        this.status = status;
        this.approved = approved;
        this.user = user;
        this.postType = postType;
        this.region = region;
        this.direction = direction;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isDeal() {
        return deal;
    }

    public void setDeal(boolean deal) {
        this.deal = deal;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(Set<PostImage> postImages) {
        this.postImages = postImages;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }


}
