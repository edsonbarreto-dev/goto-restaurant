package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reservations", schema = "gotorestaurant")
public class ReservationEntity {

    public ReservationEntity() { }

    @Id
    @UuidGenerator
    private UUID uuid;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    @OneToOne
    private CustomerEntity customerEntity;

    private LocalDate date;

    private int numberOfPeople;

    private boolean hasCancelled;
    private boolean showedUp;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_partner",
            joinColumns = @JoinColumn(name = "reservation_uuid"),
            inverseJoinColumns = @JoinColumn(name = "birthdayperson_uuid")
    )
    private List<BirthdayPersonEntity> birthdays;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
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

    public List<BirthdayPersonEntity> getBirthdays() {
        return birthdays;
    }

    public void setBirthdays(List<BirthdayPersonEntity> birthdays) {
        this.birthdays = birthdays;
    }
}
