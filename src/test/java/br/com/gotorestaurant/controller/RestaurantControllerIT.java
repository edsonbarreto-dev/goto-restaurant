package br.com.gotorestaurant.controller;

import br.com.gotorestaurant.application.controller.RestaurantController;
import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.service.RestaurantService;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.application.shared.RestaurantMapper;

import br.com.gotorestaurant.application.shared.SocialMediaMapper;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.enums.CountryCodeEnum;
import br.com.gotorestaurant.core.exceptions.BusinessException;
import br.com.gotorestaurant.core.records.Address;
import br.com.gotorestaurant.core.records.Brand;
import br.com.gotorestaurant.core.records.Phone;
import br.com.gotorestaurant.core.usecase.restaurant.implementation.create.CreateCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.implementation.create.CreateRestaurantUseCase;
import br.com.gotorestaurant.utils.CustomerHelper;
import br.com.gotorestaurant.utils.RestaurantHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;

import static br.com.gotorestaurant.utils.RestaurantHelper.registerCustomers;
import static br.com.gotorestaurant.utils.RestaurantHelper.registerSocialMedia;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)

class RestaurantControllerIT {
    @Autowired
    MockMvc mvc;

    @Autowired
    private RestaurantController controller;

    @MockBean
    private RestaurantService service;
    @Autowired
    private IRestaurantRepository repository;

    @Autowired
    private CreateRestaurantUseCase createRestaurantUseCase;
    @Autowired
    private CreateCustomerUseCase createCustomerUseCase;
    @Autowired
    private ObjectMapper mapper;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured()); // desta forma como estamos utilizando nested class gera informação duplicada
    }
    @BeforeEach
    public void up(){
        var customer = CustomerHelper.addCustomer();
        List<Customer> lista = List.of(customer);
        Restaurant restaurant = new Restaurant( "987654321","Restaurante Salsa", 200);
        restaurant.setBrand(new Brand("imagem1", "imagem2"));
        restaurant.setAddress(new Address("Rua alcantara", "23", "Vila Maria", "São Paulo", "SP", "Brasil", "02211200"));
        Phone phone = new Phone(CountryCodeEnum.BRAZIL, "11",854874585L);
        restaurant.setPhones(List.of(phone));
        restaurant.setCustomers(lista);
        restaurant.setSocialMedia(SocialMediaMapper.toListSocialMedia(registerSocialMedia()));
        createRestaurantUseCase.createRestaurant(restaurant);
    }

    @AfterEach
    void down(){
        repository.deleteAll();
    }
    @Test
    void souldAllowRegisterRestaurant() throws Exception {
        var restaurant = RestaurantHelper.cadastrarRestaurante();
        restaurant.setDocument("635336548");
        var restaurantVO = RestaurantMapper.toRestaurantVO(restaurant);

        String restauranteRequest = mapper.writeValueAsString(restaurantVO);

        System.out.println(restauranteRequest);
        mvc.perform(MockMvcRequestBuilders.post("/api/restaurant")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("UTF-8")
                .content(restauranteRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
/*
        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restaurant)
        .when()
                .post("/api/restaurant")
        .then()
                .statusCode(HttpStatus.CREATED.value());*/

    }

    @Test
    void shouldFindRestaurant(){
        given()
                .accept(ContentType.JSON)
                .when()
                    .get("/api/restaurant/find/document/{document}", 123456789)
                .then()
                    .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Registra Restaurante com Document Ja existente")
    void RegisterRestaurantWithDocumentExist() throws Exception {
        Restaurant restaurant = new Restaurant( "987654321","Restaurante Salsa", 200);
        restaurant.setBrand(new Brand("imagem1", "imagem2"));
        restaurant.setAddress(new Address("Rua alcantara", "23", "Vila Maria", "São Paulo", "SP", "Brasil", "02211200"));
        Phone phone = new Phone(CountryCodeEnum.BRAZIL, "11",854874585L);
        restaurant.setPhones(List.of(phone));
        restaurant.setCustomers(CustomerMapper.toListCustomer(registerCustomers()));
        restaurant.setSocialMedia(SocialMediaMapper.toListSocialMedia(registerSocialMedia()));
        var restaurantVO = RestaurantMapper.toRestaurantVO(restaurant);

        String restauranteRequest = mapper.writeValueAsString(restaurantVO);
        System.out.println(restauranteRequest);
        mvc.perform(MockMvcRequestBuilders.post("/api/restaurant")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("UTF-8")
                        .content(restauranteRequest))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof BusinessException))
                .andDo(MockMvcResultHandlers.print());
    }
}
