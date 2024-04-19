package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.CustomerEntityJPA;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class CustumerMapper {

    private CustumerMapper() {}

    public static CustomerEntityJPA toCustomerEntity(br.com.gotorestaurant.core.records.Customer customer) {
        CustomerEntityJPA entity = new CustomerEntityJPA();

        entity.setDocument(customer.document());
        entity.setName(customer.name());
        entity.setEmail(customer.email());
        entity.setSocialMediaEntityJPA(SocialMediaMapper.toListSocialMediaEntity(customer.socialMedia()));
        entity.setPhoneEntityJPAS(PhoneMapper.toListPhoneEntity(customer.phones()));

        return entity;
    }

    public static List<CustomerEntityJPA> toListCustomerEntity(List<br.com.gotorestaurant.core.records.Customer> customers) {
        List<CustomerEntityJPA> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Customer customer : customers) {
            entities.add(toCustomerEntity(customer));
        }
        return entities;
    }
}
