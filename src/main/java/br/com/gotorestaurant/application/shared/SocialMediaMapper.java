package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.SocialMediaEntity;
import br.com.gotorestaurant.core.records.SocialMedia;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class SocialMediaMapper {

    private SocialMediaMapper() {}

    public static SocialMediaEntity toSocialMediaEntity(SocialMedia socialMedia) {
        if (socialMedia == null) return null;
        SocialMediaEntity entity = new SocialMediaEntity();
        entity.setName(socialMedia.name());
        entity.setFullUrlPlatform(socialMedia.fullUrlPlatform());
        entity.setAccountName(socialMedia.accountName());
        entity.setReviewEntity(ReviewMapper.toListReviewEntity(socialMedia.reviews()));
        entity.setCommentEntity(CommentMapper.toListCommentEntity(socialMedia.comments()));
        return entity;
    }

    public static List<SocialMediaEntity> toListSocialMediaEntity(List<SocialMedia> socialMedias) {
        if (socialMedias == null) return null;
        List<SocialMediaEntity> entities = new ArrayList<>();
        for (SocialMedia socialMedia : socialMedias) {
            entities.add(toSocialMediaEntity(socialMedia));
        }
        return entities;
    }

    public static br.com.gotorestaurant.core.records.SocialMedia toSocialMedia(SocialMediaEntity socialMediaEntity) {
        if (socialMediaEntity == null) return null;
        return new br.com.gotorestaurant.core.records.SocialMedia(
            socialMediaEntity.getName(),
            socialMediaEntity.getAccountName(),
            socialMediaEntity.getFullUrlPlatform(),
            CommentMapper.toComments(socialMediaEntity.getCommentEntity()),
            ReviewMapper.toReview(socialMediaEntity.getReviewEntity())
        );
    }

    public static List<SocialMedia> toListSocialMedia(List<SocialMediaEntity> socialMediaEntity) {
        if (socialMediaEntity == null) return null;
        List<SocialMedia> entities = new ArrayList<>();
        for (SocialMediaEntity item : socialMediaEntity) {
            entities.add(toSocialMedia(item));
        }
        return entities;
    }
}
