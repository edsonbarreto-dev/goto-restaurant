package br.com.gotorestaurant.application.repository.entity;

import br.com.gotorestaurant.core.enums.WorkFunctionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Employee")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees", schema = "gotorestaurant")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String document;
    private String workSchedule;
    private WorkFunctionEnum workFunction;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private List<SocialMediaEntity> socialMediaEntity = new ArrayList<>();

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private List<PhoneEntity> phoneEntity = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }

    public List<br.com.gotorestaurant.application.repository.entity.SocialMediaEntity> getSocialMediaEntity() {
        return socialMediaEntity;
    }

    public void setSocialMediaEntity(List<br.com.gotorestaurant.application.repository.entity.SocialMediaEntity> socialMediaEntity) {
        this.socialMediaEntity = socialMediaEntity;
    }

    public List<PhoneEntity> getPhoneEntities() {
        return phoneEntity;
    }

    public void setPhoneEntities(List<PhoneEntity> phoneEntity) {
        this.phoneEntity = phoneEntity;
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
        return phoneEntity;
    }

    public void setPhoneEntity(List<PhoneEntity> phoneEntity) {
        this.phoneEntity = phoneEntity;
    }
}
