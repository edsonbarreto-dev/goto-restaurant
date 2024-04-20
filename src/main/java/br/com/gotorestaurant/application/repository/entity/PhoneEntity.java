package br.com.gotorestaurant.application.repository.entity;

import br.com.gotorestaurant.core.enums.CountryCodeEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "phones", schema = "gotorestaurant")
public class PhoneEntity {

    @Id
    @UuidGenerator
    private UUID uuid;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    @ManyToOne
    private EmployeeEntity employeeEntity;

    @ManyToOne
    private CustomerEntity customerEntity;

    @ManyToOne
    private SupplierEntity supplierEntity;

    @ManyToOne
    private PartnerEntity partnerEntity;

    private CountryCodeEnum countryCode;
    private String codeArea;
    private Long number;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public SupplierEntity getSupplierEntity() {
        return supplierEntity;
    }

    public void setSupplierEntity(SupplierEntity supplierEntity) {
        this.supplierEntity = supplierEntity;
    }

    public CountryCodeEnum getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCodeEnum countryCode) {
        this.countryCode = countryCode;
    }

    public String getCodeArea() {
        return codeArea;
    }

    public void setCodeArea(String codeArea) {
        this.codeArea = codeArea;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
