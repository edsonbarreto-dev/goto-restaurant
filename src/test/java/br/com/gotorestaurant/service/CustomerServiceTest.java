package br.com.gotorestaurant.service;

import br.com.gotorestaurant.application.presenter.restaurant.CustomerPresenter;
import br.com.gotorestaurant.application.presenter.restaurant.RestaurantPresenter;
import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.service.CustomerService;
import br.com.gotorestaurant.application.service.RestaurantService;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.application.shared.CustumerMapper;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.*;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerService;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.update.IUpdateCustomerUseCase;
import br.com.gotorestaurant.utils.CustomerHelper;
import br.com.gotorestaurant.utils.RestaurantHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class CustomerServiceTest {

    private ICustomerService customerService;

    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private ICreateCustomerUseCase createCustomerUseCase;

    @Mock
    private IFindCustomerUseCase findCustomerUseCase;

    @Mock
    private IUpdateCustomerUseCase updateCustomerUseCase;

    @Mock
    private CustomerPresenter customerPresenter;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepository, createCustomerUseCase,
                findCustomerUseCase);
        // restaurantService = new RestaurantService(new CreateRestaurantUseCase(findRestaurantUseCase, restaurantRepository));
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }
    @Nested
    class RegisterCustomer {
        @Test
        void shouldAllowRegisterCustomer() {
            var customer = CustomerHelper.registerCustomer();

            //var restaurantEntity = RestaurantHelper.registerRestaurant();
            when(createCustomerUseCase.createCustomer(any(Customer.class)))
                    .thenAnswer(i -> 1L);

            var restaurantCadastrado = customerService.create(CustomerMapper.toCustomer(customer));

            assertThat(restaurantCadastrado).isNotNull();
            verify(createCustomerUseCase, times(1)).
                    createCustomer(any(Customer.class));
        }

        @Test
        void shouldAllowRegisterCustomer_WhenCustomerExist() {
            var customer = CustomerHelper.addCustomer();

            when(createCustomerUseCase.createCustomer(any(Customer.class)))
                    .thenThrow(CustomerHasExistsException.class);

            Assertions.assertThatThrownBy(() -> customerService.create(customer))
                    .isInstanceOf(CustomerHasExistsException.class);

            verify(createCustomerUseCase, times(1))
                    .createCustomer(any(Customer.class));
        }
    }

    @Nested
    class FindCustomers{

        @Test
        void shouldFindCustomer(){
            //Arrange
            var id = CustomerHelper.geradorId();
            var customer = CustomerHelper.addCustomer();

            when(findCustomerUseCase.findById(any(Long.class)))
                    .thenReturn(customer);
            //Act
            var newCustomer = customerService.findBy(id);
            //Assert
            assertThat(newCustomer).isInstanceOf(Customer.class);
            verify(findCustomerUseCase, times(1))
                    .findById(any(Long.class));
        }

        @Test
        void deveGerarExcecao_QuandoBuscaCustomer_IdNaoExiste(){
            //Arrange
            var id = CustomerHelper.geradorId();

            when(findCustomerUseCase.findById(any(Long.class)))
                    .thenThrow(new CustomerNotFoundException());
            //Assert
            Assertions.assertThatThrownBy(() -> customerService.findBy(id))
                    .isInstanceOf(CustomerNotFoundException.class)
                    .hasMessage("O cliente informado não foi encontrado.");
            verify(findCustomerUseCase, times(1))
                    .findById(any(Long.class));
        }

        @Test
        void shouldFindCustomerByDocument(){
            //Arrange
            var customer = CustomerHelper.addCustomer();

            when(findCustomerUseCase.findByDocument(any(String.class)))
                    .thenReturn(customer);
            //Act
            var newCustomer = customerService.findByDocument(customer.getDocument());
            //Assert
            assertThat(newCustomer).isEqualTo(customer);
            verify(findCustomerUseCase, times(1))
                    .findByDocument(any(String.class));
        }

        @Test
        void deveGerarExcecao_QuandoBuscaCustomer_DocumentNaoExiste(){
            //Arrange
            var customer = CustomerHelper.registerCustomer();

            when(findCustomerUseCase.findByDocument(any(String.class)))
                    .thenThrow(new DocumentNullException(customer.getDocument()));
            //Assert
            Assertions.assertThatThrownBy(() -> customerService.findByDocument(customer.getDocument()))
                    .isInstanceOf(DocumentNullException.class);

            verify(findCustomerUseCase, times(1))
                    .findByDocument(any(String.class));
        }
    }
}
