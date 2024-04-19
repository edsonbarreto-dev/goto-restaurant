package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.PhoneEntityJPA;
import br.com.gotorestaurant.core.records.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class PhoneMapper {

    private PhoneMapper() {}

    public static PhoneEntityJPA toPhoneEntity(br.com.gotorestaurant.core.records.Phone phone) {
        PhoneEntityJPA entity = new PhoneEntityJPA();
        entity.setCountryCode(phone.countryCode());
        entity.setCodeArea(phone.codeArea());
        entity.setNumber(phone.number());
        return entity;
    }

    public static List<PhoneEntityJPA> toListPhoneEntity(List<br.com.gotorestaurant.core.records.Phone> phones) {
        List<PhoneEntityJPA> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Phone phone : phones) {
            entities.add(toPhoneEntity(phone));
        }
        return entities;
    }

    public static List<Phone> toListPhone(List<PhoneEntityJPA> phoneEntityJPAS) {
        List<Phone> phones = new ArrayList<>();
        for (PhoneEntityJPA entity : phoneEntityJPAS) {
            phones.add(toPhone(entity));
        }
        return phones;
    }

    private static Phone toPhone(PhoneEntityJPA entity) {
        return new Phone(entity.getCountryCode(), entity.getCodeArea(), entity.getNumber());
    }
}
