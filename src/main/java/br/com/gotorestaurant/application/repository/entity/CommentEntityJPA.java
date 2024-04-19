package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "COMMENT", schema = "TOGORESTAURANT")
public class CommentEntityJPA {
    @Id
    @UuidGenerator
    private UUID uuid;

    private String message;

    @OneToOne
    private CustomerEntityJPA customerEntityJPA;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerEntityJPA getCustomerEntityJPA() {
        return customerEntityJPA;
    }

    public void setCustomerEntityJPA(CustomerEntityJPA customerEntityJPA) {
        this.customerEntityJPA = customerEntityJPA;
    }
}
