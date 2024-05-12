package br.com.gotorestaurant.bdd;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantService;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import br.com.gotorestaurant.infra.security.TokenService;
import br.com.gotorestaurant.infra.user.User;
import br.com.gotorestaurant.utils.RestaurantHelper;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
@SpringBootTest
public class RestaurantStepDefinitions {

    private static Response response;

    private IFindRestaurantUseCase useService;
    private static TokenService tokenService;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/restaurant";

        tokenService = Mockito.mock(TokenService.class);
        Mockito.when(tokenService.generateToken(new User(1L,"marion","123456")))
                .thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVm9sbC5tZWQiLCJzdWIiOiJtYXJpb24iLCJpZCI6MSwiZXhwIjoxNzE1NDg1MjkwfQ.6m7oVNE8WkeAMtmsieMZtDXSZSSxeTc01aU2xeOV3yw");
    }


    @Given("the restaurant service is available")
    public void theRestaurantServiceIsAvailable() {
        useService = Mockito.mock(IFindRestaurantUseCase.class);
        //Restaurant restaurant = RestaurantHelper.cadastrarRestaurante();
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Mockito.when(useService.findByDocument(any(String.class))).thenReturn(restaurant);
     }

    @When("I search for a restaurant with document {string}")
    public void iSearchForRestaurantWithDocument(String document) throws InstantiationException, IllegalAccessException {
        String token = tokenService.generateToken(new User(1L,"marion","123456"));
        RequestSpecification request = given().header("Authorization", "Bearer " + token);
        response = request.when().get("find/document/" + document);
    }

    @Then("I should receive details of the restaurant")
    public void iShouldReceiveDetailsOfTheRestaurant() {
        assertEquals(200, response.getStatusCode());
        assertFalse(response.getBody().as(Restaurant.class).name().isEmpty());
        String name = response.getBody().as(Restaurant.class).name();

    }

    @Given("I have valid restaurant information")
    public void iHaveValidRestaurantInformation() {
        // This step could involve preparing valid restaurant information for creation
    }

    @When("I create a new restaurant")
    public void iCreateANewRestaurant() {
        // This step could involve making a request to create a new restaurant and storing the response
    }

    @Then("the restaurant should be successfully created")
    public void theRestaurantShouldBeSuccessfullyCreated() {
        // This step could involve verifying that the restaurant creation was successful
    }

    @Given("I have valid customer information")
    public void iHaveValidCustomerInformation() {
        // This step could involve preparing valid customer information for update
    }

    @Given("there is an existing restaurant with document {string}")
    public void thereIsAnExistingRestaurantWithDocument(String document) {
        // This step could involve ensuring that a restaurant with the given document exists in the system
    }

    @When("I update the customer information for the restaurant")
    public void iUpdateTheCustomerInformationForTheRestaurant() {
        // This step could involve making a request to update customer information for the restaurant
    }

    @Then("the customer information should be successfully updated")
    public void theCustomerInformationShouldBeSuccessfullyUpdated() {
        // This step could involve verifying that the customer information update was successful
    }
}
