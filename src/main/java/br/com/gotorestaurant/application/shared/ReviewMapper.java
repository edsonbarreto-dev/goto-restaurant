package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.ReviewEntityJPA;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class ReviewMapper {

    private ReviewMapper() {}

    public static ReviewEntityJPA toReviewEntity(br.com.gotorestaurant.core.records.Review review) {
        ReviewEntityJPA entity = new ReviewEntityJPA();
        entity.setQuestion(review.question());
        entity.setVote(review.vote());
        return entity;
    }

    public static List<ReviewEntityJPA> toListReviewEntity(List<br.com.gotorestaurant.core.records.Review> reviews) {
        List<ReviewEntityJPA> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Review review : reviews) {
            entities.add(toReviewEntity(review));
        }
        return entities;
    }

    public static List<br.com.gotorestaurant.core.records.Review> toReview(List<ReviewEntityJPA> reviewsEntity) {
        List<br.com.gotorestaurant.core.records.Review> entities = new ArrayList<>();
        for (ReviewEntityJPA reviewEntityJPA : reviewsEntity) {
            br.com.gotorestaurant.core.records.Review result;
            result = new br.com.gotorestaurant.core.records.Review(reviewEntityJPA.getQuestion(), reviewEntityJPA.getVote());
            entities.add(result);
        }
        return entities;
    }
}
