package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reservations", schema = "gotorestaurant")
public class ReservationEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    private UUID uuid;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    @ManyToOne
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "reservationEntity", cascade = CascadeType.ALL)
    private List<BirthdayPersonEntity> birthdaysPersonEntity;

    private LocalDate date;
    private int numberOfPeople;
    private boolean hasCancelled;
    private boolean showedUp;

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

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public List<BirthdayPersonEntity> getBirthdaysPersonEntity() {
        return birthdaysPersonEntity;
    }

    public void setBirthdaysPersonEntity(List<BirthdayPersonEntity> birthdaysPersonEntity) {
        this.birthdaysPersonEntity = birthdaysPersonEntity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public boolean isHasCancelled() {
        return hasCancelled;
    }

    public void setHasCancelled(boolean hasCancelled) {
        this.hasCancelled = hasCancelled;
    }

    public boolean isShowedUp() {
        return showedUp;
    }

    public void setShowedUp(boolean showedUp) {
        this.showedUp = showedUp;
    }
}
