package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "socialmedia", schema = "gotorestaurant")
public class SocialMediaEntity {

    @Id
    @UuidGenerator
    private UUID uuid;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    @ManyToOne
    private EmployeeEntity employeeEntity;

    @ManyToOne
    private CustomerEntity customerEntity;

    private String name;
    private String accountName;
    private String fullUrlPlatform;

    @OneToMany(mappedBy = "socialMediaEntity")
    private List<CommentEntity> commentEntity = List.of();

    @OneToMany(mappedBy = "socialMediaEntity")
    private List<ReviewEntity> reviewEntity;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFullUrlPlatform() {
        return fullUrlPlatform;
    }

    public void setFullUrlPlatform(String fullUrlPlatform) {
        this.fullUrlPlatform = fullUrlPlatform;
    }

    public List<CommentEntity> getCommentEntity() {
        return commentEntity;
    }

    public void setCommentEntity(List<CommentEntity> commentEntities) {
        this.commentEntity = commentEntities;
    }

    public List<ReviewEntity> getReviewEntity() {
        return reviewEntity;
    }

    public void setReviewEntity(List<ReviewEntity> reviewEntity) {
        this.reviewEntity = reviewEntity;
    }
}
