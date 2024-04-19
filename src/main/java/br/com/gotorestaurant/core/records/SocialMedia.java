package br.com.gotorestaurant.core.records;

import java.util.List;

public record SocialMedia(
    String name,
    String accountName,
    String fullUrlPlatform,
    List<Comment> comments,
    List<Review> reviews
) {}
