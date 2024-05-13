package br.com.gotorestaurant.bdd;

import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindCustomerUseCase;
import br.com.gotorestaurant.infra.security.TokenService;
import br.com.gotorestaurant.infra.user.User;
import br.com.gotorestaurant.utils.CustomerHelper;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class CustomerStepDefinition {

    private static Response response;

    private IFindCustomerUseCase service;

    @Given("the customer document is not null")
    public void theCustomerServiceIsAvailable() {
        service = Mockito.mock(IFindCustomerUseCase.class);
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(service.findByDocument(any(String.class))).thenReturn(customer);
    }

    @When("I search for a customer with document {string}")
    public void iSearchForCustomerWithDocument(String document) {
        String token = CucumberTest.getTokenService().generateToken(new User(1L,"fiap","123456"));
        RequestSpecification request = given().header("Authorization", "Bearer " + token);
        response = request.when().get("/api/customer/find/document/" + document);
    }

    @Then("I should receive details of the customer")
    public void iShouldReceiveDetailsOfTheCustomer() {
        assertEquals(200, response.getStatusCode());
        Customer customerFromResponse = response.getBody().as(Customer.class);
        assertNotNull(customerFromResponse);
        assertFalse(customerFromResponse.getName().isEmpty());
        assertTrue(customerFromResponse.getName().contains("Cliente1"));
    }

}
