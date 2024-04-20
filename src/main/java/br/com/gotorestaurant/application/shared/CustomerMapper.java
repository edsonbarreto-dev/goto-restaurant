package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.core.records.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerEntity toCustomerEntity(br.com.gotorestaurant.core.records.Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.name());
        entity.setEmail(customer.email());
        entity.setDocument(customer.document());
        entity.setSocialMediaEntity(SocialMediaMapper.toListSocialMediaEntity(customer.socialMedia()));
        entity.setPhoneEntity(PhoneMapper.toListPhoneEntity(customer.phones()));
        return entity;
    }

    public static List<CustomerEntity> toListCustomerEntity(List<br.com.gotorestaurant.core.records.Customer> customers) {
        List<CustomerEntity> entities = new ArrayList<>();
        for (br.com.gotorestaurant.core.records.Customer customer : customers) {
            entities.add(toCustomerEntity(customer));
        }
        return entities;
    }

    public static List<Customer> toListCustomer(List<CustomerEntity> listCustomerEntity) {
        List<Customer> lista = new ArrayList<>();
        listCustomerEntity.forEach(customerEntityJpa -> lista.add(toCustomer(customerEntityJpa)));
        return lista;
    }

    public static Customer toCustomer(CustomerEntity customerEntity) {
       return new Customer(
           customerEntity.getName(),
           customerEntity.getEmail(),
           customerEntity.getDocument(),
           SocialMediaMapper.toListSocialMedia(customerEntity.getSocialMediaEntity()),
           PhoneMapper.toListPhone(customerEntity.getPhoneEntity())
       );
    }
}
