package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Reservation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations", schema = "gotorestaurant")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RestaurantEntity restaurantEntity;

    @ManyToOne
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "reservationEntity", cascade = CascadeType.ALL)
    private List<BirthdayPersonEntity> birthdaysPersonEntity;

    @NotNull
    private LocalDate date;

    @NotNull
    private int numberOfPeople;

    @NotNull
    private int reservedTableNumber;

    private boolean hasCancelled;
    private boolean showedUp;

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

    public int getReservedTableNumber() {
        return reservedTableNumber;
    }

    public void setReservedTableNumber(int reservedTableNumber) {
        this.reservedTableNumber = reservedTableNumber;
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
