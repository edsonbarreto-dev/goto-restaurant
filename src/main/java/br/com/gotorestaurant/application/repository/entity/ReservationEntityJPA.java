package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "RESERVATION", schema = "TOGORESTAURANT")
public class ReservationEntityJPA {

    public ReservationEntityJPA() { }

    @Id
    @UuidGenerator
    private UUID uuid;

    @NonNull
    @OneToOne
    private CustomerEntityJPA customerEntityJPA;

    @NonNull
    private LocalDate date;

    @NonNull
    private int numberOfPeople;

    private boolean hasCancelled;
    private boolean showedUp;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_partner",
            joinColumns = @JoinColumn(name = "reservation_uuid"),
            inverseJoinColumns = @JoinColumn(name = "birthdayperson_uuid")
    )
    private List<BirthdayPersonEntityJPA> birthdays;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public CustomerEntityJPA getCustomerEntityJPA() {
        return customerEntityJPA;
    }

    public void setCustomerEntityJPA(CustomerEntityJPA customerEntityJPA) {
        this.customerEntityJPA = customerEntityJPA;
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

    public List<BirthdayPersonEntityJPA> getBirthdays() {
        return birthdays;
    }

    public void setBirthdays(List<BirthdayPersonEntityJPA> birthdays) {
        this.birthdays = birthdays;
    }
}
