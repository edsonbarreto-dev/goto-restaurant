package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.core.records.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerEntity toCustomerEntity(Customer customer) {
        if (customer == null) return null;
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.name());
        entity.setEmail(customer.email());
        entity.setDocument(customer.document());
        entity.setSocialMediaEntity(SocialMediaMapper.toListSocialMediaEntity(customer.socialMedia()));
        entity.setPhoneEntity(PhoneMapper.toListPhoneEntity(customer.phones()));
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

    public static List<Customer> toListCustomer(List<CustomerEntity> listCustomerEntity) {
        if (listCustomerEntity == null) return null;
        List<Customer> lista = new ArrayList<>();
        listCustomerEntity.forEach(customerEntityJpa -> lista.add(toCustomer(customerEntityJpa)));
        return lista;
    }

    public static Customer toCustomer(CustomerEntity customerEntity) {
        if (customerEntity == null) return null;
       return new Customer(
           customerEntity.getName(),
           customerEntity.getEmail(),
           customerEntity.getDocument(),
           SocialMediaMapper.toListSocialMedia(customerEntity.getSocialMediaEntity()),
           PhoneMapper.toListPhone(customerEntity.getPhoneEntity())
       );
    }
}
