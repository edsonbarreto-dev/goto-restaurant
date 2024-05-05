package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity(name="Restaurant")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        brand.setRestaurantEntity(this);
        this.brand = brand;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        addresses.forEach(a -> a.setRestaurantEntity(this));
        this.addresses = addresses;
    }

    public List<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneEntity> phones) {
        phones.forEach(p -> p.setRestaurantEntity(this));
        this.phones = phones;
    }

    public List<SocialMediaEntity> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMediaEntity> socialMedia) {
        socialMedia.forEach(sm -> sm.setRestaurantEntity(this));
        this.socialMedia = socialMedia;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        employees.forEach(e -> e.setRestaurantEntity(this));
        this.employees = employees;
    }

    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
    }

    public List<SupplierEntity> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierEntity> suppliers) {
        suppliers.forEach(sm -> sm.setRestaurantEntity(this));
        this.suppliers = suppliers;
    }

    public List<PartnerEntity> getPartners() {
        return partners;
    }

    public void setPartners(List<PartnerEntity> partners) {
        partners.forEach(p -> p.setRestaurantEntity(this));
        this.partners = partners;
    }

    public List<ReservationEntity> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationEntity> reservations) {
        reservations.forEach(r -> r.setRestaurantEntity(this));
        this.reservations = reservations;
    }
}
