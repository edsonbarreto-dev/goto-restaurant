package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.AddressEntity;
import br.com.gotorestaurant.core.records.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapperInterface {

    AddressEntity toAddressEntity(Address address);

    Address toCustomer(AddressEntity customerEntity);

}
