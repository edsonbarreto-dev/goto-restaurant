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

    @OneToOne
    private BrandEntity brandEntity;

    @OneToMany(mappedBy = "restaurantEntity", cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntity;

    @Transient
    private List<PhoneEntity> phoneEntity = List.of();

    @Transient
    private List<SocialMediaEntity> socialMediaEntity = List.of();

    @Transient
    private List<EmployeeEntity> employeeEntityJPA = List.of();

    @Transient
    private List<CustomerEntity> customerEntity = List.of();

    @Transient
    private List<SupplierEntity> supplierEntity = List.of();

    @Transient
    private List<PartnerEntity> partnerEntity = List.of();

    @Transient
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

    public BrandEntity getBrandEntityJPA() {
        return brandEntity;
    }

    public void setBrandEntityJPA(BrandEntity brandEntity) {
        this.brandEntity = brandEntity;
    }

    public List<AddressEntity> getAddressEntityJPA() {
        return addressEntity;
    }

    public void setAddressEntityJPA(List<AddressEntity> addressEntity) {
        this.addressEntity = addressEntity;
    }

    public List<PhoneEntity> getPhoneEntityJPA() {
        return phoneEntity;
    }

    public void setPhoneEntityJPA(List<PhoneEntity> phoneEntity) {
        this.phoneEntity = phoneEntity;
    }

    public List<SocialMediaEntity> getSocialMediaEntityJPA() {
        return socialMediaEntity;
    }

    public void setSocialMediaEntityJPA(List<SocialMediaEntity> socialMediaEntity) {
        this.socialMediaEntity = socialMediaEntity;
    }

    public List<EmployeeEntity> getEmployeeEntityJPA() {
        return employeeEntityJPA;
    }

    public void setEmployeeEntityJPA(List<EmployeeEntity> employeeEntityJPA) {
        this.employeeEntityJPA = employeeEntityJPA;
    }

    public List<CustomerEntity> getCustomerEntityJPA() {
        return customerEntity;
    }

    public void setCustomerEntityJPA(List<CustomerEntity> customerEntity) {
        this.customerEntity = customerEntity;
    }

    public List<SupplierEntity> getSupplierEntityJPA() {
        return supplierEntity;
    }

    public void setSupplierEntityJPA(List<SupplierEntity> supplierEntity) {
        this.supplierEntity = supplierEntity;
    }

    public List<PartnerEntity> getPartnerEntityJPA() {
        return partnerEntity;
    }

    public void setPartnerEntityJPA(List<PartnerEntity> partnerEntity) {
        this.partnerEntity = partnerEntity;
    }

    public List<ReservationEntity> getReservationEntityJPA() {
        return reservationEntity;
    }

    public void setReservationEntityJPA(List<ReservationEntity> reservationEntity) {
        this.reservationEntity = reservationEntity;
    }
}
