package br.com.gotorestaurant.controller;

import br.com.gotorestaurant.infra.security.TokenService;
import br.com.gotorestaurant.infra.user.User;
import br.com.gotorestaurant.utils.RestaurantHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class RestaurantControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Nested
    class RegistrarRestaurant {

        @Test
        @WithMockUser(username="marion")
        void devePermitirRegistrarRestaurant() {
            var restaurantRequest = RestaurantHelper.registerRestaurant();
            String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVm9sbC5tZWQiLCJzdWIiOiJtYXJpb24iLCJpZCI6MSwiZXhwIjoxNzE1MTI3MjA2fQ.aX3C44VWUKMW0y399BtJpv_p-Za3rc2uEUclA40bZlU";
            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .header("Authorization", "Bearer " + token)
                    .body(restaurantRequest)
            .when()
                    .post("/api/restaurant/create")
            .then()
                    .statusCode(HttpStatus.CREATED.value());
        }

        @Test
        void deveGerarExcecao_QuandoRegistrarMensagem_DocumentEmBranco() {
            var restaurantRequest = RestaurantHelper.cadastrarRestaurante();
            restaurantRequest.setDocument("");

            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(restaurantRequest)
                    .when()
                    .post("/api/restaurant/create")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("$", hasKey("message"))
                    .body("$", hasKey("errors"))
                    .body("message", equalTo("Validation error"))
                    .body("errors[0]", equalTo("Documento não pode estar vazio"));
        }

        @Test
        void deveGerarExcecao_QuandoRegistrarRestaurant_NameEmBranco() {
            var restaurantRequest = RestaurantHelper.cadastrarRestaurante();
            restaurantRequest.setName("");

            given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(restaurantRequest)
                    .when()
                    .post("/api/restaurant/create")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("$", hasKey("message"))
                    .body("$", hasKey("errors"))
                    .body("message", equalTo("Validation error"))
                    .body("errors[0]", equalTo("Nome do restaurante não pode estar vazio"));
        }

        @Test
        void deveGerarExcecao_QuandoRegistrarMensagem_PayloadComXml() {
            String xmlPayload = "<restaurant><name>John</name><document>638292736</document><capacity>200</capacity></restaurant>";

            given()
                    .contentType(MediaType.APPLICATION_XML_VALUE)
                    .body(xmlPayload)
                    .when()
                    .post("/api/restaurant/create")
                    .then()
                    .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        }
    }

}
