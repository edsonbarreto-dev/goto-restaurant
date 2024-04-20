package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.AddressEntity;
import br.com.gotorestaurant.core.records.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AddressMapper {

    private AddressMapper() {}

    public static AddressEntity toAddressEntity(Address address) {
        AddressEntity entity = new AddressEntity();
        entity.setCity(address.city());
        entity.setCountry(address.country());
        entity.setState(address.state());
        entity.setZipCode(address.zipCode());
        entity.setNeighborhood(address.neighborhood());
        entity.setPublicPlace(address.publicPlace());
        entity.setNumber(address.number());
        entity.setZipCode(address.zipCode());
        return entity;
    }

    public static List<AddressEntity> toListAddressEntitiesJPA(List<Address> addresses) {
        List<AddressEntity> entities = new ArrayList<>();
        for (Address address : addresses) {
            entities.add(toAddressEntity(address));
        }
        return entities;
    }

    public static br.com.gotorestaurant.core.records.Address toAddress(AddressEntity addressEntity) {
        return new br.com.gotorestaurant.core.records.Address(
            addressEntity.getPublicPlace(),
            addressEntity.getNumber(),
            addressEntity.getNeighborhood(),
            addressEntity.getCity(),
            addressEntity.getState(),
            addressEntity.getCountry(),
            addressEntity.getZipCode()
        );
    }

    public static List<Address> toListAddressEntities(List<AddressEntity> addressEntityList) {
        List<Address> entities = new ArrayList<>();
        for (AddressEntity addressEntity : addressEntityList) {
            entities.add(toAddress(addressEntity));
        }
        return entities;
    }
}
