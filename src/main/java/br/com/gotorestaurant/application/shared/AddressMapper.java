package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.AddressMapperInterface;
import br.com.gotorestaurant.application.repository.entity.AddressEntityJPA;
import br.com.gotorestaurant.core.records.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AddressMapper {

    private AddressMapper() {}

    public static AddressEntityJPA toAddressEntity(Address address) {
        AddressEntityJPA entity = new AddressEntityJPA();
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

    public static List<AddressEntityJPA> toListAddressEntitiesJPA(List<Address> addresses) {
        List<AddressEntityJPA> entities = new ArrayList<>();
        for (Address address : addresses) {
            entities.add(toAddressEntity(address));
        }
        return entities;
    }

    public static br.com.gotorestaurant.core.records.Address toAddress(AddressEntityJPA addressEntityJPA) {
        return new br.com.gotorestaurant.core.records.Address(
            addressEntityJPA.getPublicPlace(),
            addressEntityJPA.getNumber(),
            addressEntityJPA.getNeighborhood(),
            addressEntityJPA.getCity(),
            addressEntityJPA.getState(),
            addressEntityJPA.getCountry(),
            addressEntityJPA.getZipCode()
        );
    }

    public static List<Address> toListAddressEntities(List<AddressEntityJPA> addressEntityJPAList) {
        List<Address> entities = new ArrayList<>();
        for (AddressEntityJPA addressEntityJPA : addressEntityJPAList) {
            entities.add(toAddress(addressEntityJPA));
        }
        return entities;
    }
}
