package br.com.gotorestaurant.performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.util.UUID;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ApiPerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json");

    ActionBuilder adicinarRestaurantRequest = http("adicionar restaurante")
            .post("/api/restaurant")
            .body(StringBody("{ \"name\": \"Restaurante\", \"document\": \""+ UUID.randomUUID().toString() + "\", \"capacity\": \"capacity\",  }"))
            .check(status().is(201));
           // .check(jsonPath("$.id").saveAs("id"));

    ActionBuilder buscarRestaurantRequest = http("buscar restaurante")
            .get("/api/restaurant/find/document/#{document}")
            .check(status().is(200));


    ScenarioBuilder cenarioAdicionarRestaurant = scenario("Adicionar restaurante")
            .exec(adicinarRestaurantRequest);

    ScenarioBuilder cenarioAdicionarBuscarRestaurante = scenario("Adicionar e Buscar restaurante")
            .exec(adicinarRestaurantRequest)
            .exec(buscarRestaurantRequest);

    {
        setUp(
                cenarioAdicionarRestaurant.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10))),
                cenarioAdicionarBuscarRestaurante.injectOpen(
                        rampUsersPerSec(1)
                                .to(30)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(30)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(30)
                                .to(1)
                                .during(Duration.ofSeconds(10))))

                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(50),
                        global().failedRequests().count().is(0L));
    }
}
