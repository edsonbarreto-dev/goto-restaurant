package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;


import java.util.List;


@Entity(name = "Customer")
@Table(name = "customers", schema = "gotorestaurant")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<RestaurantEntity> restaurantFK;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
    private List<SocialMediaEntity> socialMedia;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
    private List<PhoneEntity> phones;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
    private List<ReservationEntity> reservations;

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

    public List<RestaurantEntity> getRestaurantFK() {
        return restaurantFK;
    }

    public void setRestaurantFK(List<RestaurantEntity> restaurantEntities) {
        this.restaurantFK = restaurantEntities;
    }

    public List<SocialMediaEntity> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMediaEntity> socialMediaEntity) {
        this.socialMedia = socialMediaEntity;
    }

    public List<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneEntity> list) {
        this.phones = list;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservationEntity) {
        this.reservations = reservationEntity;
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
