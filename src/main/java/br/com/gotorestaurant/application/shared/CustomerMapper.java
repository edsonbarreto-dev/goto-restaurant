package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.CustomerEntityJPA;
import br.com.gotorestaurant.application.repository.entity.PhoneEntityJPA;
import br.com.gotorestaurant.application.repository.entity.SocialMediaEntityJPA;
import br.com.gotorestaurant.core.records.Customer;
import br.com.gotorestaurant.core.records.Phone;
import br.com.gotorestaurant.core.records.SocialMedia;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerEntityJPA toCustomerEntity(br.com.gotorestaurant.core.records.Customer customer) {
        CustomerEntityJPA entity = new CustomerEntityJPA();
        entity.setName(customer.name());
        entity.setEmail(customer.email());
        entity.setDocument(customer.document());
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

    public static List<Customer> toListCustomer(List<CustomerEntityJPA> listCustomerEntityJPA) {
        List<Customer> lista = new ArrayList<>();
        listCustomerEntityJPA.forEach(customerEntityJpa -> lista.add(toCustomer(customerEntityJpa)));
        return lista;
    }

    public static Customer toCustomer(CustomerEntityJPA customerEntityJPA) {
       return new Customer(
           customerEntityJPA.getName(),
           customerEntityJPA.getEmail(),
           customerEntityJPA.getDocument(),
           SocialMediaMapper.toListSocialMedia(customerEntityJPA.getSocialMediaEntityJPA()),
           PhoneMapper.toListPhone(customerEntityJPA.getPhoneEntityJPAS())
       );
    }
}
