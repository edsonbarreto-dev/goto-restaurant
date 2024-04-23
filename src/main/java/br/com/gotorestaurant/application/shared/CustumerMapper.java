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
        CustomerEntity entity = new CustomerEntity();

        entity.setDocument(customer.document());
        entity.setName(customer.name());
        entity.setEmail(customer.email());
        entity.setSocialMediaEntity(SocialMediaMapper.toListSocialMediaEntity(customer.socialMedia()));
        entity.setPhoneEntity(PhoneMapper.toListPhoneEntity(customer.phones()));

        return entity;
    }

    public static List<CustomerEntity> toListCustomerEntity(List<br.com.gotorestaurant.core.records.Customer> customers) {
        List<CustomerEntity> entities = new ArrayList<>();
        for (Customer customer : customers) {
            entities.add(toCustomerEntity(customer));
        }
        return entities;
    }
}
