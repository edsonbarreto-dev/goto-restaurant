package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;


import java.util.List;


@Entity(name = "Customer")
@Table(name = "customers", schema = "gotorestaurant")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "customerEntity")
    private List<RestaurantEntity> restaurantEntities;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<SocialMediaEntity> socialMediaEntity;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<PhoneEntity> phoneEntities;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<ReservationEntity> reservationEntity;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String document;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RestaurantEntity> getRestaurantEntities() {
        return restaurantEntities;
    }

    public void setRestaurantEntities(List<RestaurantEntity> restaurantEntities) {
        this.restaurantEntities = restaurantEntities;
    }

    public List<SocialMediaEntity> getSocialMediaEntity() {
        return socialMediaEntity;
    }

    public void setSocialMediaEntity(List<SocialMediaEntity> socialMediaEntity) {
        this.socialMediaEntity = socialMediaEntity;
    }

    public List<PhoneEntity> getPhoneEntities() {
        return phoneEntities;
    }

    public void setPhoneEntities(List<PhoneEntity> list) {
        this.phoneEntities = list;
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
