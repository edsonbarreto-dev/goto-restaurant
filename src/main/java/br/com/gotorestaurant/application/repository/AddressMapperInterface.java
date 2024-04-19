package br.com.gotorestaurant.application.repository;

import br.com.gotorestaurant.application.repository.entity.AddressEntityJPA;
import br.com.gotorestaurant.core.records.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapperInterface {

    AddressEntityJPA toAddressEntity(Address address);

    Address toCustomer(AddressEntityJPA customerEntity);

}
