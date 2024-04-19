package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "RESTAURANT", schema = "GOTORESTAURANT")
public class RestaurantEntityJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String document;

    private String name;

    private int capacity;

    @Transient
    private BrandEntityJPA brandEntityJPA;

    @Transient
    private List<AddressEntityJPA> addressEntityJPA;

    @Transient
    private List<PhoneEntityJPA> phoneEntityJPA = List.of();

    @Transient
    private List<SocialMediaEntityJPA> socialMediaEntityJPA = List.of();

    @Transient
    private List<EmployeeEntityJPA> employeeEntityJPA = List.of();

    @Transient
    private List<CustomerEntityJPA> customerEntityJPA = List.of();

    @Transient
    private List<SupplierEntityJPA> supplierEntityJPA = List.of();

    @Transient
    private List<PartnerEntityJPA> partnerEntityJPA = List.of();

    @Transient
    private List<ReservationEntityJPA> reservationEntityJPA = List.of();

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

    public BrandEntityJPA getBrandEntityJPA() {
        return brandEntityJPA;
    }

    public void setBrandEntityJPA(BrandEntityJPA brandEntityJPA) {
        this.brandEntityJPA = brandEntityJPA;
    }

    public List<AddressEntityJPA> getAddressEntityJPA() {
        return addressEntityJPA;
    }

    public void setAddressEntityJPA(List<AddressEntityJPA> addressEntityJPA) {
        this.addressEntityJPA = addressEntityJPA;
    }

    public List<PhoneEntityJPA> getPhoneEntityJPA() {
        return phoneEntityJPA;
    }

    public void setPhoneEntityJPA(List<PhoneEntityJPA> phoneEntityJPA) {
        this.phoneEntityJPA = phoneEntityJPA;
    }

    public List<SocialMediaEntityJPA> getSocialMediaEntityJPA() {
        return socialMediaEntityJPA;
    }

    public void setSocialMediaEntityJPA(List<SocialMediaEntityJPA> socialMediaEntityJPA) {
        this.socialMediaEntityJPA = socialMediaEntityJPA;
    }

    public List<EmployeeEntityJPA> getEmployeeEntityJPA() {
        return employeeEntityJPA;
    }

    public void setEmployeeEntityJPA(List<EmployeeEntityJPA> employeeEntityJPA) {
        this.employeeEntityJPA = employeeEntityJPA;
    }

    public List<CustomerEntityJPA> getCustomerEntityJPA() {
        return customerEntityJPA;
    }

    public void setCustomerEntityJPA(List<CustomerEntityJPA> customerEntityJPA) {
        this.customerEntityJPA = customerEntityJPA;
    }

    public List<SupplierEntityJPA> getSupplierEntityJPA() {
        return supplierEntityJPA;
    }

    public void setSupplierEntityJPA(List<SupplierEntityJPA> supplierEntityJPA) {
        this.supplierEntityJPA = supplierEntityJPA;
    }

    public List<PartnerEntityJPA> getPartnerEntityJPA() {
        return partnerEntityJPA;
    }

    public void setPartnerEntityJPA(List<PartnerEntityJPA> partnerEntityJPA) {
        this.partnerEntityJPA = partnerEntityJPA;
    }

    public List<ReservationEntityJPA> getReservationEntityJPA() {
        return reservationEntityJPA;
    }

    public void setReservationEntityJPA(List<ReservationEntityJPA> reservationEntityJPA) {
        this.reservationEntityJPA = reservationEntityJPA;
    }
}
