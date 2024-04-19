package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.SocialMediaEntityJPA;
import br.com.gotorestaurant.core.records.SocialMedia;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class SocialMediaMapper {

    private SocialMediaMapper() {}

    public static SocialMediaEntityJPA toSocialMediaEntity(br.com.gotorestaurant.core.records.SocialMedia socialMedia) {
        SocialMediaEntityJPA entity = new SocialMediaEntityJPA();
        entity.setName(socialMedia.name());
        entity.setFullUrlPlatform(socialMedia.fullUrlPlatform());
        entity.setAccountName(socialMedia.accountName());
        entity.setReviewEntityJPA(ReviewMapper.toListReviewEntity(socialMedia.reviews()));
        entity.setCommentEntityJPAS(CommentMapper.toListCommentEntity(socialMedia.comments()));
        return entity;
    }

    public static List<SocialMediaEntityJPA> toListSocialMediaEntity(List<br.com.gotorestaurant.core.records.SocialMedia> socialMedias) {
        List<SocialMediaEntityJPA> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.SocialMedia socialMedia : socialMedias) {
            entities.add(toSocialMediaEntity(socialMedia));
        }
        return entities;
    }

    public static br.com.gotorestaurant.core.records.SocialMedia toSocialMedia(SocialMediaEntityJPA socialMediaEntityJPA) {
        return new br.com.gotorestaurant.core.records.SocialMedia(
            socialMediaEntityJPA.getName(),
            socialMediaEntityJPA.getAccountName(),
            socialMediaEntityJPA.getFullUrlPlatform(),
            CommentMapper.toComments(socialMediaEntityJPA.getCommentEntityJPAS()),
            ReviewMapper.toReview(socialMediaEntityJPA.getReviewEntityJPA())
        );
    }

    public static List<SocialMedia> toListSocialMedia(List<SocialMediaEntityJPA> socialMediaEntityJPA) {
        List<SocialMedia> entities = new ArrayList<>();
        for (SocialMediaEntityJPA socialMediaEntity : socialMediaEntityJPA) {
            entities.add(toSocialMedia(socialMediaEntity));
        }
        return entities;
    }
}
