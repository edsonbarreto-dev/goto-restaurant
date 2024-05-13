package br.com.gotorestaurant.repository;

import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.utils.CustomerHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)

class CostumerRepositoryTestIT {

    @Autowired
    private ICustomerRepository customerRepository;

    @Test
    void shouldAllowFindByName(){
        var customer = CustomerHelper.registerCustomer();
        var customerAdd = cadastrar(customer);
        var customerOptional = customerRepository.findByName(customer.getName());
        // Assert
        assertThat(customerOptional)
                .isPresent();
        customerOptional.ifPresent(newCustomer -> {
            assertThat(newCustomer.getId())
                    .isEqualTo(customer.getId());
            assertThat(newCustomer.getDocument())
                    .isEqualTo(customer.getDocument());
            assertThat(newCustomer.getEmail())
                    .isEqualTo(customer.getEmail());

        });
    }

    @Test
    void shouldAllowFindByDocument(){
        var customer = CustomerHelper.registerCustomer();
        var customerAdd = cadastrar(customer);
        var customerOptional = customerRepository.findByDocument(customer.getDocument());
        // Assert
        assertThat(customerOptional)
                .isPresent();
        customerOptional.ifPresent(newCustomer -> {
            assertThat(newCustomer.getId())
                    .isEqualTo(customer.getId());
            assertThat(newCustomer.getName())
                    .isEqualTo(customer.getName());
            assertThat(newCustomer.getEmail())
                    .isEqualTo(customer.getEmail());

        });
    }

    @Test
    void shouldAllowFindByEmail(){
        var customer = CustomerHelper.registerCustomer();
        var customerAdd = cadastrar(customer);
        var customerOptional = customerRepository.findByEmail(customer.getEmail());
        // Assert
        assertThat(customerOptional)
                .isPresent();
        customerOptional.ifPresent(newCustomer -> {
            assertThat(newCustomer.getId())
                    .isEqualTo(customer.getId());
            assertThat(newCustomer.getName())
                    .isEqualTo(customer.getName());
            assertThat(newCustomer.getDocument())
                    .isEqualTo(customer.getDocument());
        });
    }
    private CustomerEntity cadastrar(CustomerEntity customer){
        return customerRepository.save(customer);
    }
}
/*
Optional<CustomerEntity> findByName(String name);
Optional<CustomerEntity> findByDocument(String document);
Optional<CustomerEntity> findByEmail(String email);*/