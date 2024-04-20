package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.CommentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class CommentMapper {

    private CommentMapper() {}

    public static CommentEntity toCommentEntity(br.com.gotorestaurant.core.records.Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.setCustomerEntity(CustomerMapper.toCustomerEntity(comment.customer()));
        entity.setMessage(comment.message());
        return entity;
    }

    public static List<CommentEntity> toListCommentEntity(List<br.com.gotorestaurant.core.records.Comment> comments) {
        List<CommentEntity> list = new ArrayList<>();
        comments.forEach(comment -> list.add(toCommentEntity(comment)));
        return list;
    }

    public static List<br.com.gotorestaurant.core.records.Comment> toComments(List<CommentEntity> commentEntities) {
        List<br.com.gotorestaurant.core.records.Comment> list = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            var item = new br.com.gotorestaurant.core.records.Comment(
                    commentEntity.getMessage(), CustomerMapper.toCustomer(commentEntity.getCustomerEntity())
            );
            list.add(item);
        }
        return list;
    }
}
