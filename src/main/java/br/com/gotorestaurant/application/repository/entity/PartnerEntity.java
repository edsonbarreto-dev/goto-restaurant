package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "partners", schema = "gotorestaurant")
public class PartnerEntity {
    @Id
    @UuidGenerator
    private UUID uuid;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    private String name;
    private String email;
    private String document;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "partner_socialmedia",
            joinColumns = @JoinColumn(name = "partner_uuid"),
            inverseJoinColumns = @JoinColumn(name = "socialmedia_uuid")
    )
    private List<SocialMediaEntity> socialMediaEntity = List.of();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "partner_phones",
            joinColumns = @JoinColumn(name = "partner_uuid"),
            inverseJoinColumns = @JoinColumn(name = "phone_uuid")
    )
    private List<PhoneEntity> phoneEntity = List.of();

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
}
