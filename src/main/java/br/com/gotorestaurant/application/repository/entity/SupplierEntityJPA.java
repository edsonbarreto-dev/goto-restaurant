package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "EMPLOYEE", schema = "TOGORESTAURANT")
public class SupplierEntityJPA {
    @Id
    @UuidGenerator
    private UUID uuid;

    private String name;
    private String email;
    private String document;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "supplier_socialmedia",
            joinColumns = @JoinColumn(name = "supplier_uuid"),
            inverseJoinColumns = @JoinColumn(name = "socialmedia_uuid")
    )
    private List<SocialMediaEntityJPA> socialMediaEntityJPA = List.of();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "supplier_phones",
            joinColumns = @JoinColumn(name = "supplier_uuid"),
            inverseJoinColumns = @JoinColumn(name = "phone_uuid")
    )
    private List<PhoneEntityJPA> phoneEntityJPAS = List.of();

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

    public List<SocialMediaEntityJPA> getSocialMediaEntityJPA() {
        return socialMediaEntityJPA;
    }

    public void setSocialMediaEntityJPA(List<SocialMediaEntityJPA> socialMediaEntityJPA) {
        this.socialMediaEntityJPA = socialMediaEntityJPA;
    }

    public List<PhoneEntityJPA> getPhoneEntityJPAS() {
        return phoneEntityJPAS;
    }

    public void setPhoneEntityJPAS(List<PhoneEntityJPA> phoneEntityJPAS) {
        this.phoneEntityJPAS = phoneEntityJPAS;
    }
}
