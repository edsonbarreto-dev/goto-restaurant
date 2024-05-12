package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.BirthdayPersonEntity;
import br.com.gotorestaurant.core.records.BirthdayPerson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class BirthdayPersonMapper {

    private BirthdayPersonMapper() {}

    public static BirthdayPersonEntity toBirthdayPersonEntity(BirthdayPerson birthdayPerson) {
        if (birthdayPerson == null) return null;
        BirthdayPersonEntity entity = new BirthdayPersonEntity();
        entity.setName(birthdayPerson.name());
        entity.setAge(birthdayPerson.age());
        entity.setGender(birthdayPerson.gender());
        entity.setBirthdayMonth(birthdayPerson.birthdayMonth());
        entity.setBirthday(birthdayPerson.birthday());
        entity.setWishHouseCelebration(birthdayPerson.wishHouseCelebration());
        return entity;
    }

    public static List<BirthdayPersonEntity> toListBirthdayPersonEntity(List<BirthdayPerson> birthdayPersons) {
        if (birthdayPersons == null) return null;
        List<BirthdayPersonEntity> entities = new ArrayList<>();
        for (BirthdayPerson birthdayPerson : birthdayPersons) {
            entities.add(toBirthdayPersonEntity(birthdayPerson));
        }
        return entities;
    }

    public static List<BirthdayPerson> toListBirthDayPerson(List<BirthdayPersonEntity> birthdays) {
        if (birthdays == null) return null;
        List<BirthdayPerson> entities = new ArrayList<>();
        for (BirthdayPersonEntity birthday : birthdays) {
            entities.add(toBirthday(birthday));
        }
        return entities;
    }

    private static BirthdayPerson toBirthday(BirthdayPersonEntity birthday) {
        if (birthday == null) return null;
        return new BirthdayPerson(
            birthday.getName(),
            birthday.getAge(),
            birthday.getBirthday(),
            birthday.getBirthdayMonth(),
            birthday.getGender(),
            birthday.isWishHouseCelebration()
        );
    }
}
