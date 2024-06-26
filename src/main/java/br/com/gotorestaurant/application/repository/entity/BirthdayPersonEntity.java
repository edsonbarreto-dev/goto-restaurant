package br.com.gotorestaurant.application.repository.entity;

import br.com.gotorestaurant.core.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "BirthdayPerson")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "birthdays", schema = "gotorestaurant")
public class BirthdayPersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ReservationEntity reservationEntity;

    private String name;
    private int age;
    private int birthday;
    private int birthdayMonth;
    private GenderEnum gender;
    private boolean wishHouseCelebration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(ReservationEntity reservationEntity) {
        this.reservationEntity = reservationEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public int getBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(int birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public boolean isWishHouseCelebration() {
        return wishHouseCelebration;
    }

    public void setWishHouseCelebration(boolean wishHouseCelebration) {
        this.wishHouseCelebration = wishHouseCelebration;
    }
}
