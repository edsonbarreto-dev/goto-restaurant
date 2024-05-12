package br.com.gotorestaurant.application.record;

import br.com.gotorestaurant.core.records.Comment;
import br.com.gotorestaurant.core.records.Review;

import java.util.List;

public record SocialMediaVO(
        String name,
        String accountName,
        String fullUrlPlatform,
        List<Comment> comments,
        List<Review> reviews
) {
}
