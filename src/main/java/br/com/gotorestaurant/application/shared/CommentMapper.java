package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.CommentEntityJPA;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class CommentMapper {

    private CommentMapper() {}

    public static CommentEntityJPA toCommentEntity(br.com.gotorestaurant.core.records.Comment comment) {
        CommentEntityJPA entity = new CommentEntityJPA();
        entity.setCustomerEntityJPA(CustomerMapper.toCustomerEntity(comment.customer()));
        entity.setMessage(comment.message());
        return entity;
    }

    public static List<CommentEntityJPA> toListCommentEntity(List<br.com.gotorestaurant.core.records.Comment> comments) {
        List<CommentEntityJPA> list = new ArrayList<>();
        comments.forEach(comment -> list.add(toCommentEntity(comment)));
        return list;
    }

    public static List<br.com.gotorestaurant.core.records.Comment> toComments(List<CommentEntityJPA> commentEntityJPAS) {
        List<br.com.gotorestaurant.core.records.Comment> list = new ArrayList<>();
        for (CommentEntityJPA commentEntityJPA : commentEntityJPAS) {
            var item = new br.com.gotorestaurant.core.records.Comment(
                    commentEntityJPA.getMessage(), CustomerMapper.toCustomer(commentEntityJPA.getCustomerEntityJPA())
            );
            list.add(item);
        }
        return list;
    }
}
