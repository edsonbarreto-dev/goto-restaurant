package br.com.gotorestaurant.application.repository.entity;

import br.com.gotorestaurant.core.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "BIRTHDAYPERSON", schema = "TOGORESTAURANT")
public class BirthdayPersonEntityJPA {
    @Id
    @UuidGenerator
    private UUID uuid;

    private String name;
    private int age;
    private int birthday;
    private int birthdayMonth;
    private GenderEnum gender;
    private boolean wishHouseCelebration;

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
