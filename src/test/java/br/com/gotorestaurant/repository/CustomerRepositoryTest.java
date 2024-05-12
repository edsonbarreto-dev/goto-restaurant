package br.com.gotorestaurant.repository;

import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.utils.CustomerHelper;
import br.com.gotorestaurant.utils.RestaurantHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerRepositoryTest {

    @Mock
    private ICustomerRepository customerRepository;
    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }
    @Test
    void findByNameCustomer(){
        var customer = CustomerHelper.registerCustomer();

        when(customerRepository.findByName(any(String.class)))
                .thenReturn(Optional.of(customer));

        //Act
        var customerFoundOptional = customerRepository.findByName(customer.getName());
        //Assert
        assertThat(customerFoundOptional)
                .isPresent()
                .containsSame(customer);
        customerFoundOptional.ifPresent(customerFounded -> {
            assertThat(customerFounded.getId()).isEqualTo(customer.getId());
            assertThat(customerFounded.getName()).isEqualTo(customer.getName());
        });
        verify(customerRepository, times(1))
                .findByName(any(String.class));
    }
    @Test
    void findByDocumentCustomer(){
        var customer = CustomerHelper.registerCustomer();

        when(customerRepository.findByDocument(any(String.class)))
                .thenReturn(Optional.of(customer));

        //Act
        var customerFoundOptional = customerRepository.findByDocument(customer.getDocument());
        //Assert
        assertThat(customerFoundOptional)
                .isPresent()
                .containsSame(customer);
        customerFoundOptional.ifPresent(customerFounded -> {
            assertThat(customerFounded.getId()).isEqualTo(customer.getId());
            assertThat(customerFounded.getName()).isEqualTo(customer.getName());
        });
        verify(customerRepository, times(1))
                .findByDocument(any(String.class));
    }
    @Test
    void findByEmailCustomer(){
        var customer = CustomerHelper.registerCustomer();

        when(customerRepository.findByEmail(any(String.class)))
                .thenReturn(Optional.of(customer));

        //Act
        var customerFoundOptional = customerRepository.findByEmail(customer.getEmail());
        //Assert
        assertThat(customerFoundOptional)
                .isPresent()
                .containsSame(customer);
        customerFoundOptional.ifPresent(customerFounded -> {
            assertThat(customerFounded.getId()).isEqualTo(customer.getId());
            assertThat(customerFounded.getName()).isEqualTo(customer.getName());
        });
        verify(customerRepository, times(1))
                .findByEmail(any(String.class));
    }
    
    void updateCustomer(){
        var newCustomer  = CustomerHelper.registerCustomer();
        newCustomer.setEmail("novoEmail@teste.com");

        doNothing().when(customerRepository).updateCustomer(newCustomer);

        customerRepository.updateCustomer(newCustomer);

        verify(customerRepository, times(1))
                .updateCustomer(any(CustomerEntity.class));
    }
}
