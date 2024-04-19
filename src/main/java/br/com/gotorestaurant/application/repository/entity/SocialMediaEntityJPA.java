package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "SOCIALMEDIA", schema = "TOGORESTAURANT")
public class SocialMediaEntityJPA {

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
    private List<CommentEntityJPA> commentEntityJPAS = List.of();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "socialmedia_review",
            joinColumns = @JoinColumn(name = "socialmedia_uuid"),
            inverseJoinColumns = @JoinColumn(name = "review_uuid")
    )
    private List<ReviewEntityJPA> reviewEntityJPA;

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

    public List<CommentEntityJPA> getCommentEntityJPAS() {
        return commentEntityJPAS;
    }

    public void setCommentEntityJPAS(List<CommentEntityJPA> commentEntityJPAS) {
        this.commentEntityJPAS = commentEntityJPAS;
    }

    public List<ReviewEntityJPA> getReviewEntityJPA() {
        return reviewEntityJPA;
    }

    public void setReviewEntityJPA(List<ReviewEntityJPA> reviewEntityJPA) {
        this.reviewEntityJPA = reviewEntityJPA;
    }
}
