package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity(name="Restaurant")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "restaurants", schema = "gotorestaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String document;

    private String name;

    private int capacity;

    @OneToOne(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private BrandEntity brand;

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AddressEntity> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhoneEntity> phones = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SocialMediaEntity> socialMedia = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmployeeEntity> employees = new ArrayList<>();

    @ManyToMany(mappedBy = "restaurantFK", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CustomerEntity> customers = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SupplierEntity> suppliers = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PartnerEntity> partners = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReservationEntity> reservations = new ArrayList<>();


    public void setBrand(BrandEntity brand) {
        brand.setRestaurantEntity(this);
        this.brand = brand;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        addresses.forEach(a -> a.setRestaurantEntity(this));
        this.addresses = addresses;
    }

    public void setPhones(List<PhoneEntity> phones) {
        phones.forEach(p -> p.setRestaurantEntity(this));
        this.phones = phones;
    }

    public void setSocialMedia(List<SocialMediaEntity> socialMedia) {
        socialMedia.forEach(sm -> sm.setRestaurantEntity(this));
        this.socialMedia = socialMedia;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        employees.forEach(e -> e.setRestaurantEntity(this));
        this.employees = employees;
    }

    public void setSuppliers(List<SupplierEntity> suppliers) {
        suppliers.forEach(sm -> sm.setRestaurantEntity(this));
        this.suppliers = suppliers;
    }

    public void setPartners(List<PartnerEntity> partners) {
        partners.forEach(p -> p.setRestaurantEntity(this));
        this.partners = partners;
    }


    public void setReservations(List<ReservationEntity> reservations) {
        reservations.forEach(r -> r.setRestaurantEntity(this));
        this.reservations = reservations;
    }
}
