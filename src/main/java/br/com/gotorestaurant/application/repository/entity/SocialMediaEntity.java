package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "socialmedia", schema = "gotorestaurant")
public class SocialMediaEntity {

    @Id
    @UuidGenerator
    private UUID uuid;

    private String name;
    private String accountName;
    private String fullUrlPlatform;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "socialmedia_comments",
            joinColumns = @JoinColumn(name = "socialmedia_uuid"),
            inverseJoinColumns = @JoinColumn(name = "comments_uuid")
    )
    private List<CommentEntity> commentEntities = List.of();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "socialmedia_review",
            joinColumns = @JoinColumn(name = "socialmedia_uuid"),
            inverseJoinColumns = @JoinColumn(name = "review_uuid")
    )
    private List<ReviewEntity> reviewEntity;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFullUrlPlatform() {
        return fullUrlPlatform;
    }

    public void setFullUrlPlatform(String fullUrlPlatform) {
        this.fullUrlPlatform = fullUrlPlatform;
    }

    public List<CommentEntity> getCommentEntity() {
        return commentEntities;
    }

    public void setCommentEntity(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public List<ReviewEntity> getReviewEntity() {
        return reviewEntity;
    }

    public void setReviewEntity(List<ReviewEntity> reviewEntity) {
        this.reviewEntity = reviewEntity;
    }
}
