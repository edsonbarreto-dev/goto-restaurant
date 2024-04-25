package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "socialmedia", schema = "gotorestaurant")
public class SocialMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    @ManyToOne
    private EmployeeEntity employeeEntity;

    @ManyToOne
    private CustomerEntity customerEntity;

    @ManyToOne
    private SupplierEntity supplierEntity;

    @ManyToOne
    private PartnerEntity partnerEntity;

    @OneToMany(mappedBy = "socialMediaEntity")
    private List<CommentEntity> commentEntity = new ArrayList<>();

    @OneToMany(mappedBy = "socialMediaEntity")
    private List<ReviewEntity> reviewEntity;

    private String name;
    private String accountName;
    private String fullUrlPlatform;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public SupplierEntity getSupplierEntity() {
        return supplierEntity;
    }

    public void setSupplierEntity(SupplierEntity supplierEntity) {
        this.supplierEntity = supplierEntity;
    }

    public PartnerEntity getPartnerEntity() {
        return partnerEntity;
    }

    public void setPartnerEntity(PartnerEntity partnerEntity) {
        this.partnerEntity = partnerEntity;
    }

    public List<CommentEntity> getCommentEntity() {
        return commentEntity;
    }

    public void setCommentEntity(List<CommentEntity> commentEntity) {
        this.commentEntity = commentEntity;
    }

    public List<ReviewEntity> getReviewEntity() {
        return reviewEntity;
    }

    public void setReviewEntity(List<ReviewEntity> reviewEntity) {
        this.reviewEntity = reviewEntity;
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
}
