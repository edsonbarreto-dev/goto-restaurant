package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.ReviewEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class ReviewMapper {

    private ReviewMapper() {}

    public static ReviewEntity toReviewEntity(br.com.gotorestaurant.core.records.Review review) {
        ReviewEntity entity = new ReviewEntity();
        entity.setQuestion(review.question());
        entity.setVote(review.vote());
        return entity;
    }

    public static List<ReviewEntity> toListReviewEntity(List<br.com.gotorestaurant.core.records.Review> reviews) {
        List<ReviewEntity> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Review review : reviews) {
            entities.add(toReviewEntity(review));
        }
        return entities;
    }

    public static List<br.com.gotorestaurant.core.records.Review> toReview(List<ReviewEntity> reviewsEntity) {
        List<br.com.gotorestaurant.core.records.Review> entities = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewsEntity) {
            br.com.gotorestaurant.core.records.Review result;
            result = new br.com.gotorestaurant.core.records.Review(reviewEntity.getQuestion(), reviewEntity.getVote());
            entities.add(result);
        }
        return entities;
    }
}
