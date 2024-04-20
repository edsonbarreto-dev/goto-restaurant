package br.com.gotorestaurant.application.repository.entity;

import br.com.gotorestaurant.core.enums.WorkFunctionEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employees", schema = "gotorestaurant")
public class EmployeeEntity {
    @Id
    @UuidGenerator
    private UUID uuid;
    private String name;
    private String email;
    private String document;
    private String workSchedule;
    private WorkFunctionEnum workFunction;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private List<SocialMediaEntity> SocialMediaEntity = List.of();

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private List<PhoneEntity> phoneEntities = List.of();

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

    public List<br.com.gotorestaurant.application.repository.entity.SocialMediaEntity> getSocialMediaEntity() {
        return SocialMediaEntity;
    }

    public void setSocialMediaEntity(List<br.com.gotorestaurant.application.repository.entity.SocialMediaEntity> socialMediaEntity) {
        SocialMediaEntity = socialMediaEntity;
    }

    public List<PhoneEntity> getPhoneEntities() {
        return phoneEntities;
    }

    public void setPhoneEntities(List<PhoneEntity> phoneEntities) {
        this.phoneEntities = phoneEntities;
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

    public String getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }

    public WorkFunctionEnum getWorkFunction() {
        return workFunction;
    }

    public void setWorkFunction(WorkFunctionEnum workFunction) {
        this.workFunction = workFunction;
    }

    public List<PhoneEntity> getPhoneEntity() {
        return phoneEntities;
    }

    public void setPhoneEntity(List<PhoneEntity> phoneEntities) {
        this.phoneEntities = phoneEntities;
    }
}
