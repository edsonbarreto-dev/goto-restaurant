package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;


import java.util.List;


@Entity
@Table(name = "customers", schema = "gotorestaurant")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<SocialMediaEntity> socialMediaEntity;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<PhoneEntity> phoneEntity;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<ReservationEntity> reservationEntity;

    private String name;
    private String email;
    private String document;

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

    public List<SocialMediaEntity> getSocialMediaEntity() {
        return socialMediaEntity;
    }

    public void setSocialMediaEntity(List<SocialMediaEntity> socialMediaEntity) {
        this.socialMediaEntity = socialMediaEntity;
    }

    public List<PhoneEntity> getPhoneEntity() {
        return phoneEntity;
    }

    public void setPhoneEntity(List<PhoneEntity> phoneEntity) {
        this.phoneEntity = phoneEntity;
    }

    public List<ReservationEntity> getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(List<ReservationEntity> reservationEntity) {
        this.reservationEntity = reservationEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
