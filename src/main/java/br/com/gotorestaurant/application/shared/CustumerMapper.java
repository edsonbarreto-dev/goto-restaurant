package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.core.records.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class CustumerMapper {

    private CustumerMapper() {}

    public static CustomerEntity toCustomerEntity(Customer customer) {
        if (customer == null) return null;
        CustomerEntity entity = new CustomerEntity();
        entity.setDocument(customer.document());
        entity.setName(customer.name());
        entity.setEmail(customer.email());
        entity.setSocialMedia(SocialMediaMapper.toListSocialMediaEntity(customer.socialMedia()));
        entity.setPhones(PhoneMapper.fromListCoreToListEntity(customer.phones()));
        return entity;
    }

    public static List<CustomerEntity> toListCustomerEntity(List<Customer> customers) {
        if (customers == null) return null;
        List<CustomerEntity> entities = new ArrayList<>();
        for (Customer customer : customers) {
            entities.add(toCustomerEntity(customer));
        }
        return entities;
    }
}
