package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.record.CustomerVO;
import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.application.repository.entity.PhoneEntity;
import br.com.gotorestaurant.application.repository.entity.SocialMediaEntity;
import br.com.gotorestaurant.core.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerEntity toCustomerEntity(Customer customer) {
        if (customer == null) return null;
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setDocument(customer.getDocument());
        entity.setSocialMediaEntity(SocialMediaMapper.toListSocialMediaEntity(customer.getSocialMedia()));
        entity.setPhoneEntities(PhoneMapper.fromListCoreToListEntity(customer.getPhones()));
        return entity;
    }

    public static Customer toCustomerCore(br.com.gotorestaurant.core.records.Customer customer) {
        if (customer == null) return null;
        Customer c = new Customer(
                customer.document(),
                customer.name(),
                customer.email()
        );
        c.addListPhone(PhoneMapper.fromListCoreToListEntity(customer.phones()));
        c.updateAllSocialMedia(customer.socialMedia());
        return c;
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

    public static Customer toCustomer(CustomerVO customerVO) {
        if (customerVO == null) return null;
        return new Customer( customerVO.document(), customerVO.name(), customerVO.email() );
    }

    public static br.com.gotorestaurant.core.records.Customer fromCustomerVOtoCustomerCore(CustomerVO customerVO) {
        if (customerVO == null) return null;
        return new br.com.gotorestaurant.core.records.Customer(
            customerVO.name(),
            customerVO.email(),
            customerVO.document(),
            new ArrayList<>(),
            PhoneMapper.toListPhoneRecord(customerVO.phones())
        );
    }

    public static Customer toCustomer(CustomerEntity customerEntity) {
        if (customerEntity == null) return null;
        Customer customer = new Customer(
                customerEntity.getDocument(),
                customerEntity.getName(),
                customerEntity.getEmail()
        );
        for (SocialMediaEntity socialMediaEntity : customerEntity.getSocialMediaEntity()) {
            customer.addSocialMedia(SocialMediaMapper.toSocialMedia(socialMediaEntity));
        }
        for (PhoneEntity phoneEntity : customerEntity.getPhoneEntities()) {
            customer.addPhone(PhoneMapper.toPhone(phoneEntity));
        }
        return customer;
    }
}
