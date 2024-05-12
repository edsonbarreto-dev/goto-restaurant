package br.com.gotorestaurant.core.entity;

import br.com.gotorestaurant.core.exceptions.NameNullException;
import br.com.gotorestaurant.core.records.Comment;
import br.com.gotorestaurant.core.records.Review;
import br.com.gotorestaurant.core.shared.ValidateShared;

import java.util.List;

public class SocialMedia {

    private String name;
    private String accountName;
    private String fullUrlPlatform;
    private List<Comment> comments = List.of();
    private List<Review> reviews = List.of();
    private String subject;


    public SocialMedia(String name, String accountName, String fullUrlPlatform) {
        subject = "Social Media";

        this.verifyName(name);
        this.verifyAccountName(accountName);
        this.verifyAccountFullUrlPlatform(fullUrlPlatform);

        this.name = name;
        this.accountName = accountName;
        this.fullUrlPlatform = fullUrlPlatform;
    }

    private void verifyName(String name) {
        if (name == null || name.isBlank()) throw new NameNullException(subject);
    }

    private void verifyAccountName(String accountName) {
        ValidateShared.validateAccountName(accountName);
    }

    private void verifyAccountFullUrlPlatform(String fullUrlPlatform) {
        ValidateShared.validateFullUrlPlatform(fullUrlPlatform);
    }

    public String getName() {
        return name;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getFullUrlPlatform() {
        return fullUrlPlatform;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
