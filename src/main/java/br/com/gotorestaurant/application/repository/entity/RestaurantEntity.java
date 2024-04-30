package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
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
    private BrandEntity brandEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntity;

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhoneEntity> phoneEntity = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SocialMediaEntity> socialMediaEntity = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmployeeEntity> employeeEntity = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="restaurant_costumer",
            joinColumns=@JoinColumn(name="restaurant_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="costumer_id", referencedColumnName="id"))
    private List<CustomerEntity> customerEntity = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SupplierEntity> supplierEntity = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PartnerEntity> partnerEntity = new ArrayList<>();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ReservationEntity> reservationEntity = new ArrayList<>();

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

    public BrandEntity getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(BrandEntity brandEntity) {
        brandEntity.setRestaurantEntity(this);
        this.brandEntity = brandEntity;
    }

    public List<AddressEntity> getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(List<AddressEntity> addressEntity) {
        addressEntity.forEach(a -> a.setRestaurantEntity(this));
        this.addressEntity = addressEntity;
    }

    public List<PhoneEntity> getPhoneEntity() {
        return phoneEntity;
    }

    public void setPhoneEntity(List<PhoneEntity> phoneEntity) {
        phoneEntity.forEach(p -> p.setRestaurantEntity(this));
        this.phoneEntity = phoneEntity;
    }

    public List<SocialMediaEntity> getSocialMediaEntity() {
        return socialMediaEntity;
    }

    public void setSocialMediaEntity(List<SocialMediaEntity> socialMediaEntity) {
        socialMediaEntity.forEach(sm -> sm.setRestaurantEntity(this));
        this.socialMediaEntity = socialMediaEntity;
    }

    public List<EmployeeEntity> getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(List<EmployeeEntity> employeeEntity) {
        employeeEntity.forEach(e -> e.setRestaurantEntity(this));
        this.employeeEntity = employeeEntity;
    }

    public List<CustomerEntity> getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(List<CustomerEntity> customerEntity) {
        this.customerEntity = customerEntity;
    }

    public List<SupplierEntity> getSupplierEntity() {
        return supplierEntity;
    }

    public void setSupplierEntity(List<SupplierEntity> supplierEntity) {
        supplierEntity.forEach(sm -> sm.setRestaurantEntity(this));
        this.supplierEntity = supplierEntity;
    }

    public List<PartnerEntity> getPartnerEntity() {
        return partnerEntity;
    }

    public void setPartnerEntity(List<PartnerEntity> partnerEntity) {
        partnerEntity.forEach(p -> p.setRestaurantEntity(this));
        this.partnerEntity = partnerEntity;
    }

    public List<ReservationEntity> getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(List<ReservationEntity> reservationEntity) {
        reservationEntity.forEach(r -> r.setRestaurantEntity(this));
        this.reservationEntity = reservationEntity;
    }

    @ManyToMany
    private Collection<CustomerEntity> customerEntities;

    public Collection<CustomerEntity> getCustomerEntities() {
        return customerEntities;
    }

    public void setCustomerEntities(Collection<CustomerEntity> customerEntities) {
        this.customerEntities = customerEntities;
    }
}
