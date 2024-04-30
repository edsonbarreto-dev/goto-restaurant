package br.com.gotorestaurant.application.presenter.restaurant;

import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IPhoneRepository;
import br.com.gotorestaurant.application.repository.ISocialMediaRepository;
import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.application.repository.entity.PhoneEntity;
import br.com.gotorestaurant.application.repository.entity.SocialMediaEntity;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.application.shared.PhoneMapper;
import br.com.gotorestaurant.application.shared.SocialMediaMapper;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.exceptions.CustomerNotFoundException;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CustomerPresenter implements ICustomerPresenter {

    @Autowired
    private ICustomerRepository repository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Autowired
    private ISocialMediaRepository socialMediaRepository;

    @Transactional
    @Override
    public Long createCustomer(Customer customer) {
        CustomerEntity add = CustomerMapper.toCustomerEntity(customer);
        CustomerEntity entity = this.repository.save(add);
        return entity.getId();
    }

    @Transactional
    @Override
    public void updateCustomer(Customer customer) {
        CustomerEntity customerEntity = this.repository.findByDocument(customer.getDocument())
                .orElseThrow(CustomerNotFoundException::new);

        List<PhoneEntity> listPhoneEntity = PhoneMapper.toListPhoneEntity(customer.getPhones());
        List<SocialMediaEntity> listSocialMediaEntity = SocialMediaMapper.toListSocialMediaEntity(customer.getSocialMedia());

        listPhoneEntity.forEach(phoneEntity -> phoneEntity.setCustomerEntity(customerEntity));
        customerEntity.setPhoneEntities(listPhoneEntity);

        listSocialMediaEntity.forEach(socialMediaEntity -> socialMediaEntity.setCustomerEntity(customerEntity));
        customerEntity.setSocialMediaEntity(listSocialMediaEntity);

        this.phoneRepository.saveAll(listPhoneEntity);
        this.socialMediaRepository.saveAll(listSocialMediaEntity);
        this.repository.save(customerEntity);
    }

    @Override
    public Customer findById(Long id) {
        CustomerEntity restaurantEntity = this.repository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
        return CustomerMapper.toCustomer(restaurantEntity);
    }

    @Override
    public Customer findByDocument(String document) {
        CustomerEntity restaurantEntity = this.repository.findByDocument(document)
                .orElseThrow(CustomerNotFoundException::new);
        return CustomerMapper.toCustomer(restaurantEntity);
    }

    @Override
    public Customer findByName(String name) {
        CustomerEntity customer = this.repository.findByName(name)
                .orElseThrow(CustomerNotFoundException::new);
        return CustomerMapper.toCustomer(customer);

    }

    @Override
    public Customer findByEmail(String email) {
        CustomerEntity customer = this.repository.findByEmail(email)
                .orElseThrow(CustomerNotFoundException::new);
        return CustomerMapper.toCustomer(customer);
    }
}
