package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.PhoneEntity;
import br.com.gotorestaurant.core.records.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class PhoneMapper {

    private PhoneMapper() {}

    public static PhoneEntity toPhoneEntity(Phone phone) {
        PhoneEntity entity = new PhoneEntity();
        entity.setCountryCode(phone.countryCode());
        entity.setCodeArea(phone.codeArea());
        entity.setNumber(phone.number());
        return entity;
    }

    public static List<PhoneEntity> toListPhoneEntity(List<br.com.gotorestaurant.core.records.Phone> phones) {
        List<PhoneEntity> entities = new ArrayList<>();
        for (Phone phone : phones) {
            entities.add(toPhoneEntity(phone));
        }
        return entities;
    }

    public static List<Phone> toListPhone(List<PhoneEntity> phoneEntity) {
        List<Phone> phones = new ArrayList<>();
        for (PhoneEntity entity : phoneEntity) {
            phones.add(toPhone(entity));
        }
        return phones;
    }

    private static Phone toPhone(PhoneEntity entity) {
        return new Phone(entity.getCountryCode(), entity.getCodeArea(), entity.getNumber());
    }
}
