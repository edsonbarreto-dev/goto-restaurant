package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.record.SocialMediaVO;
import br.com.gotorestaurant.application.repository.entity.SocialMediaEntity;
import br.com.gotorestaurant.core.entity.SocialMedia;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class SocialMediaMapper {

    private SocialMediaMapper() {}

    public static SocialMediaEntity toSocialMediaEntity(SocialMedia socialMedia) {
        if (socialMedia == null) return null;
        SocialMediaEntity entity = new SocialMediaEntity();
        entity.setName(socialMedia.getName());
        entity.setFullUrlPlatform(socialMedia.getFullUrlPlatform());
        entity.setAccountName(socialMedia.getAccountName());
        entity.setReviewEntity(ReviewMapper.toListReviewEntity(socialMedia.getReviews()));
        entity.setCommentEntity(CommentMapper.toListCommentEntity(socialMedia.getComments()));
        return entity;
    }

    public static SocialMediaEntity toSocialMediaEntity(br.com.gotorestaurant.core.records.SocialMedia socialMedia) {
        if (socialMedia == null) return null;
        SocialMediaEntity entity = new SocialMediaEntity();
        entity.setName(socialMedia.name());
        entity.setFullUrlPlatform(socialMedia.fullUrlPlatform());
        entity.setAccountName(socialMedia.accountName());
        entity.setReviewEntity(ReviewMapper.toListReviewEntity(socialMedia.reviews()));
        entity.setCommentEntity(CommentMapper.toListCommentEntity(socialMedia.comments()));
        return entity;
    }

    public static List<SocialMediaEntity> toListSocialMediaEntity(List<br.com.gotorestaurant.core.records.SocialMedia> socialMedias) {
        if (socialMedias == null) return null;
        List<SocialMediaEntity> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.SocialMedia socialMediaItem : socialMedias) {
            entities.add(toSocialMediaEntity(socialMediaItem));
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

    public static List<br.com.gotorestaurant.core.records.SocialMedia> toListSocialMedia(List<SocialMediaEntity> listSocialMediaEntity) {
        if (listSocialMediaEntity == null) return null;
        ArrayList<br.com.gotorestaurant.core.records.SocialMedia> listSocialMedia = new ArrayList<>();

        listSocialMediaEntity.forEach(socialMediaEntity -> listSocialMedia.add(toSocialMedia(socialMediaEntity)));

        return listSocialMedia;
    }

    public static List<br.com.gotorestaurant.core.records.SocialMedia> toSocialMediaRecord(List<SocialMediaEntity> socialMedia) {
        if (socialMedia == null) return null;

        List<br.com.gotorestaurant.core.records.SocialMedia> entities = new ArrayList<>();

        for (SocialMediaEntity socialMediaItem : socialMedia) {
            entities.add(
                new br.com.gotorestaurant.core.records.SocialMedia(
                    socialMediaItem.getName(),
                    socialMediaItem.getAccountName(),
                    socialMediaItem.getFullUrlPlatform(),
                    CommentMapper.toComments(socialMediaItem.getCommentEntity()),
                    ReviewMapper.toReview(socialMediaItem.getReviewEntity())
                )
            );
        }
        return entities;
    }

    public static br.com.gotorestaurant.core.entity.SocialMedia toSocialMedia(br.com.gotorestaurant.core.records.SocialMedia socialMediaRecord) {
        if (socialMediaRecord == null) return null;
        return new br.com.gotorestaurant.core.entity.SocialMedia(
            socialMediaRecord.name(),
            socialMediaRecord.accountName(),
            socialMediaRecord.fullUrlPlatform()
        );
    }

    public static SocialMedia toSocialMedia(SocialMediaVO socialMediaVO) {
        if (socialMediaVO == null) return null;
        return new SocialMedia(
            socialMediaVO.name(),
            socialMediaVO.accountName(),
            socialMediaVO.fullUrlPlatform()
        );
    }

    public static List<br.com.gotorestaurant.core.records.SocialMedia> toSocialMedia(List<SocialMedia> socialMedia) {
        if (socialMedia == null) return null;
        ArrayList<br.com.gotorestaurant.core.records.SocialMedia> socialMediaRecord = new ArrayList<>();
        socialMedia.forEach(sm ->
            socialMediaRecord.add(
                new br.com.gotorestaurant.core.records.SocialMedia(
                        sm.getName(), sm.getAccountName(), sm.getFullUrlPlatform(),sm.getComments(), sm.getReviews()
                )
            )
        );
        return socialMediaRecord;
    }
}
