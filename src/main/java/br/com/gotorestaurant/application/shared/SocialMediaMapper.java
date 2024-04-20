package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.SocialMediaEntity;
import br.com.gotorestaurant.core.records.SocialMedia;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class SocialMediaMapper {

    private SocialMediaMapper() {}

    public static SocialMediaEntity toSocialMediaEntity(br.com.gotorestaurant.core.records.SocialMedia socialMedia) {
        SocialMediaEntity entity = new SocialMediaEntity();
        entity.setName(socialMedia.name());
        entity.setFullUrlPlatform(socialMedia.fullUrlPlatform());
        entity.setAccountName(socialMedia.accountName());
        entity.setReviewEntityJPA(ReviewMapper.toListReviewEntity(socialMedia.reviews()));
        entity.setCommentEntityJPAS(CommentMapper.toListCommentEntity(socialMedia.comments()));
        return entity;
    }

    public static List<SocialMediaEntity> toListSocialMediaEntity(List<br.com.gotorestaurant.core.records.SocialMedia> socialMedias) {
        List<SocialMediaEntity> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.SocialMedia socialMedia : socialMedias) {
            entities.add(toSocialMediaEntity(socialMedia));
        }
        return entities;
    }

    public static br.com.gotorestaurant.core.records.SocialMedia toSocialMedia(SocialMediaEntity socialMediaEntity) {
        return new br.com.gotorestaurant.core.records.SocialMedia(
            socialMediaEntity.getName(),
            socialMediaEntity.getAccountName(),
            socialMediaEntity.getFullUrlPlatform(),
            CommentMapper.toComments(socialMediaEntity.getCommentEntityJPAS()),
            ReviewMapper.toReview(socialMediaEntity.getReviewEntityJPA())
        );
    }

    public static List<SocialMedia> toListSocialMedia(List<SocialMediaEntity> socialMediaEntityJPA) {
        List<SocialMedia> entities = new ArrayList<>();
        for (SocialMediaEntity socialMediaEntity : socialMediaEntityJPA) {
            entities.add(toSocialMedia(socialMediaEntity));
        }
        return entities;
    }
}
