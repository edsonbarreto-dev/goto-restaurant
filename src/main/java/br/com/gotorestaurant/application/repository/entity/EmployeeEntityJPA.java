package br.com.gotorestaurant.application.repository.entity;

import br.com.gotorestaurant.core.enums.WorkFunctionEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "EMPLOYEE", schema = "TOGORESTAURANT")
public class EmployeeEntityJPA {
    @Id
    @UuidGenerator
    private UUID uuid;
    private String name;
    private String email;
    private String document;
    private String workSchedule;
    private WorkFunctionEnum workFunction;

    @Transient
    private List<SocialMediaEntityJPA> socialMediaEntityJPA;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_phones",
            joinColumns = @JoinColumn(name = "employee_uuid"),
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
