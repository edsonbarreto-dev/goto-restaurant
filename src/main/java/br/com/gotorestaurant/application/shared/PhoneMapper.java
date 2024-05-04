package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.record.PhoneVO;
import br.com.gotorestaurant.application.repository.entity.PhoneEntity;
import br.com.gotorestaurant.core.records.Phone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class PhoneMapper {

    private PhoneMapper() {}

    public static PhoneEntity fromCoreToEntity(Phone phone) {
        if (phone == null) return null;
        PhoneEntity entity = new PhoneEntity();
        entity.setCountryCode(phone.countryCode());
        entity.setCodeArea(phone.codeArea());
        entity.setNumber(phone.number());
        return entity;
    }

    public static List<PhoneEntity> fromListCoreToListEntity(List<Phone> list) {
        if (list == null) return null;
        List<PhoneEntity> fones = new ArrayList<>();
        for (Phone phone : list) {
            fones.add(fromCoreToEntity(phone));
        }
        return fones;
    }

    public static List<Phone> fromListEntityToListCore(List<PhoneEntity> list) {
        if (list == null) return null;
        List<Phone> phones = new ArrayList<>();
        for (PhoneEntity entity : list) {
            phones.add(toPhone(entity));
        }
        return phones;
    }

    public static Phone toPhone(PhoneEntity entity) {
        if (entity == null) return null;
        return new Phone(entity.getCountryCode(), entity.getCodeArea(), entity.getNumber());
    }

    public static List<Phone> toListPhoneRecord(List<PhoneVO> phonesVO) {
        if (phonesVO == null) return new ArrayList<>();
        List<Phone> phones = new ArrayList<>();
        for (PhoneVO phoneVO : phonesVO) {
            phones.add(toPhoneRecord(phoneVO));
        }
        return phones;
    }

    public static Phone toPhoneRecord(PhoneVO phoneVO) {
        if (phoneVO == null) return null;
        return new Phone(phoneVO.countryCode(), phoneVO.codeArea(), phoneVO.number());
    }
}
