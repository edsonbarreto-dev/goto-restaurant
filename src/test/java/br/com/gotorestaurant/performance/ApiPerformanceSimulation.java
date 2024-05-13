package br.com.gotorestaurant.performance;

import br.com.gotorestaurant.infra.security.TokenService;
import br.com.gotorestaurant.infra.user.User;
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
    private String generateBearerToken() {
        TokenService tokenService = new TokenService("$2a$10$O6nJCM1NIK3KRLMHl/y1bOrA4W7oRv30SSNG8YDlwqXW0VCwg3l4G");
        User user = new User(1L,"marion", "123456");
        return tokenService.generateToken(user);
    }
    String bearerToken = generateBearerToken();
    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json")
            .authorizationHeader("Bearer " + bearerToken);


    String documentValue = UUID.randomUUID().toString();

            //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVm9sbC5tZWQiLCJzdWIiOiJtYXJpb24iLCJpZCI6MSwiZXhwIjoxNzE1NTY0MDQxfQ.UL7ZmYxNUdSYt5qmoW7wcyaUFOHuiWEMUJ7_fcEoO3E";
    ActionBuilder adicinarCustomerRequest = http("adicionar cliente")
            .post(session -> "/api/customer")
                    .header("Authorization", "Bearer ${bearerToken}")
            .body(StringBody("{ \"name\": \"NomeCliente\", \"document\": \""+ documentValue + "\", \"email\": \"email@test.com\"  }"))
            .check(status().is(201));
    ActionBuilder buscarCustomerRequest = http("buscar cliente")
            .get(session -> "/api/customer/find/document/" + documentValue)
            .header("Authorization", "Bearer ${bearerToken}")
            .check(status().is(200));


    ScenarioBuilder cenarioAdicionarCliente = scenario("Adicionar cliente")
            .exec(session -> session.set("bearerToken", bearerToken))
            .exec(adicinarCustomerRequest);

    ScenarioBuilder cenarioAdicionarBuscarCliente = scenario("Adicionar e Buscar cliente")
            .exec(session -> session.set("bearerToken", bearerToken))
            .exec(adicinarCustomerRequest)
            .exec(buscarCustomerRequest);

    {
        setUp(
                cenarioAdicionarCliente.injectOpen(
                        rampUsersPerSec(1)
                                .to(10)
                                .during(Duration.ofSeconds(10)),
                        constantUsersPerSec(10)
                                .during(Duration.ofSeconds(60)),
                        rampUsersPerSec(10)
                                .to(1)
                                .during(Duration.ofSeconds(10))),
                cenarioAdicionarBuscarCliente.injectOpen(
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
