package br.com.gotorestaurant.controller;

import br.com.gotorestaurant.utils.RestaurantHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantControllerITError {

    @LocalServerPort
    private int port;

    private String token;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Nested
    class RegistrarRestaurant {

        @BeforeEach
        public void setupToken(){
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVm9sbC5tZWQiLCJzdWIiOiJtYXJpb24iLCJpZCI6MSwiZXhwIjoxNzE1Mzg0NjAxfQ.rqTKeG5qL1kub4t7k930-mfseYLwowCt93IAdwmlCGA";
        }
        @Test
        @WithMockUser(username="marion")
        void devePermitirRegistrarRestaurant() {
            var restaurantRequest = RestaurantHelper.registerRestaurant();
             given()
                    .filter(new AllureRestAssured())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .header("Authorization", "Bearer " + token)
                    .body(restaurantRequest)
            .when()
                    .post("/api/restaurant")
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
                    .post("/api/restaurant")
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
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(restaurantRequest)
            .when()
                    .post("/api/restaurant")
            .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .body("$", hasKey("message"))
                    .body("$", hasKey("errors"))
                    .body("message", equalTo("Validation error"))
                    .body("errors[0]", equalTo("O nome da RestaurantEntity precisa ser preenchido."));
        }

    }

}
