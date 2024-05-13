package br.com.gotorestaurant.controller;


import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.enums.CountryCodeEnum;
import br.com.gotorestaurant.core.records.Phone;
import br.com.gotorestaurant.core.records.SocialMedia;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import br.com.gotorestaurant.utils.CustomerHelper;
import br.com.gotorestaurant.utils.RestaurantHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerTestIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ICustomerRepository repository;

    @Autowired
    private ICreateCustomerUseCase createCustomerUseCase;

    @Autowired
    private IFindRestaurantUseCase findRestaurantUseCase;

    @Autowired
    private ObjectMapper mapper;
    @BeforeEach
    void up(){
        Customer customer = new Customer("987654321","Cliente1", "teset@teset.com");
        customer.addPhone(new Phone(CountryCodeEnum.BRAZIL,"11",847494849L));
        customer.addSocialMedia(new SocialMedia("teste", "testeAccount", "URL", null, null));
        createCustomerUseCase.createCustomer(customer);
    }

    @AfterEach
    void down(){
        repository.deleteAll();
    }
    @Test
    @DisplayName("Buscar cliente por documento")
    void findCustomer() throws Exception {
        String document = "987654321";
        mvc.perform(MockMvcRequestBuilders.get("/api/customer/find/document/{document}", document))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Registrar cliente")
    void registerCustomer() throws Exception {
        CustomerEntity customer = new CustomerEntity();
        customer.setId(14879858988L);
        customer.setDocument("12" + customer.getId());
        customer.setEmail("Cliente" + customer.getId() + "@teset.com");
        customer.setName("Cliente" + customer.getId());
        customer.setRestaurantFK(RestaurantHelper.restaurantes());
        customer.setPhones(RestaurantHelper.registerPhone());
        customer.setSocialMedia(RestaurantHelper.registerSocialMedia());
        var customerVo = CustomerMapper.toCustomer(customer);

        String customerRequest = mapper.writeValueAsString(customerVo);
        mvc.perform(MockMvcRequestBuilders.post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                .content(customerRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Salvar cliente que já existe")
    void registerCustomerQuandoJaExiste() throws Exception {
        var customer = CustomerHelper.registerCustomer();
        customer.setDocument("987654321");
        var customerVo = CustomerMapper.toCustomer(customer);

        String customerRequest = mapper.writeValueAsString(customerVo);
        mvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
