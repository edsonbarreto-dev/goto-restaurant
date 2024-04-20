package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurants", schema = "gotorestaurant")
public class RestaurantEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;

    private String document;

    private String name;

    private int capacity;

    @OneToOne(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private BrandEntity brandEntity;

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntity;

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL )
    private List<PhoneEntity> phoneEntity = List.of();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<SocialMediaEntity> socialMediaEntity = List.of();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<EmployeeEntity> employeeEntity = List.of();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<CustomerEntity> customerEntity = List.of();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<SupplierEntity> supplierEntity = List.of();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<PartnerEntity> partnerEntity = List.of();

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<ReservationEntity> reservationEntity = List.of();

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
        this.brandEntity = brandEntity;
    }

    public List<AddressEntity> getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(List<AddressEntity> addressEntity) {
        this.addressEntity = addressEntity;
    }

    public List<PhoneEntity> getPhoneEntity() {
        return phoneEntity;
    }

    public void setPhoneEntity(List<PhoneEntity> phoneEntity) {
        this.phoneEntity = phoneEntity;
    }

    public List<SocialMediaEntity> getSocialMediaEntity() {
        return socialMediaEntity;
    }

    public void setSocialMediaEntity(List<SocialMediaEntity> socialMediaEntity) {
        this.socialMediaEntity = socialMediaEntity;
    }

    public List<EmployeeEntity> getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(List<EmployeeEntity> employeeEntity) {
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
        this.supplierEntity = supplierEntity;
    }

    public List<PartnerEntity> getPartnerEntity() {
        return partnerEntity;
    }

    public void setPartnerEntity(List<PartnerEntity> partnerEntity) {
        this.partnerEntity = partnerEntity;
    }

    public List<ReservationEntity> getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(List<ReservationEntity> reservationEntity) {
        this.reservationEntity = reservationEntity;
    }
}
