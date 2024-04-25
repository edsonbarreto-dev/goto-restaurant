package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;




@Entity
@Table(name = "comments", schema = "gotorestaurant")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private SocialMediaEntity socialMediaEntity;

    @OneToOne
    private CustomerEntity customerEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialMediaEntity getSocialMediaEntity() {
        return socialMediaEntity;
    }

    public void setSocialMediaEntity(SocialMediaEntity socialMediaEntity) {
        this.socialMediaEntity = socialMediaEntity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }
}
