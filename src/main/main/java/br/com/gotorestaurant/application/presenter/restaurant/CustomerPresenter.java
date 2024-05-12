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
    private ICustomerRepository customerRepository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Autowired
    private ISocialMediaRepository socialMediaRepository;

    @Transactional
    @Override
    public Long createCustomer(Customer customer) {
        CustomerEntity add = CustomerMapper.toCustomerEntity(customer);
        CustomerEntity entity = this.customerRepository.save(add);
        return entity.getId();
    }

    @Transactional
    @Override
    public void updateCustomer(Customer customer) {
        CustomerEntity customerFind = this.customerRepository.findByDocument(customer.getDocument())
                .orElseThrow(CustomerNotFoundException::new);

        List<PhoneEntity> phones = PhoneMapper.fromListCoreToListEntity(customer.getPhones());
        List<SocialMediaEntity> listSocialMediaEntity = SocialMediaMapper.toListSocialMediaEntity(customer.getSocialMedia());

        phones.forEach(phone -> phone.setCustomerEntity(customerFind));
        customerFind.setPhones(phones);

        listSocialMediaEntity.forEach(socialMediaEntity -> socialMediaEntity.setCustomerEntity(customerFind));
        customerFind.setSocialMedia(listSocialMediaEntity);

        this.phoneRepository.saveAll(phones);
        this.socialMediaRepository.saveAll(listSocialMediaEntity);
        this.customerRepository.save(customerFind);
    }

    @Override
    public Customer findById(Long id) {
        CustomerEntity restaurantEntity = this.customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
        return CustomerMapper.toCustomer(restaurantEntity);
    }

    @Override
    public Customer findByDocument(String document) {
        CustomerEntity restaurantEntity = this.customerRepository.findByDocument(document)
                .orElseThrow(CustomerNotFoundException::new);
        return CustomerMapper.toCustomer(restaurantEntity);
    }

    @Override
    public Customer findByName(String name) {
        CustomerEntity customer = this.customerRepository.findByName(name)
                .orElseThrow(CustomerNotFoundException::new);
        return CustomerMapper.toCustomer(customer);

    }

    @Override
    public Customer findByEmail(String email) {
        CustomerEntity customer = this.customerRepository.findByEmail(email)
                .orElseThrow(CustomerNotFoundException::new);
        return CustomerMapper.toCustomer(customer);
    }
}
