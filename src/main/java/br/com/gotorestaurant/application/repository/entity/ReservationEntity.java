package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity(name = "Reservation")
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

    private LocalDate date;
    private int numberOfPeople;
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
