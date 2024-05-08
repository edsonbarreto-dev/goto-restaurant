package br.com.gotorestaurant.bdd;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.utils.RestaurantHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;

public class DefinicaoPassos {

    private Response response;

    private Restaurant restaurantResponse;

    private String token;

    private String ENDPOINT_RESTAURANT = "http://localhost:8080/api/restaurant";
    private String endpoint = "http://localhost:8080/api/login";
    @Dado("Eu tenho um token de autenticação valido")
    public void euTenhoUmTokenDeAutenticaçãoValido() {
        // Lógica para obter um token de autenticação válido
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVm9sbC5tZWQiLCJzdWIiOiJtYXJpb24iLCJpZCI6MSwiZXhwIjoxNzE1MTMyMTE5fQ.QO04LltaSK3COPGKUI2ymeAFQ7ePsWP5qMES1pF_MYc";
    }

    @Quando("Eu faço uma solicitação GET para {string} com o token de autenticação")
    public void euFaçoUmaSolicitaçãoGETParaComOTokenDeAutenticação(String endpoint) {
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        Response response = request.post(endpoint);
        // Armazenar a resposta para verificação posterior
    }

    @Então("Eu devo receber um código de resposta {int}")
    public void euDevoReceberUmCódigoDeResposta(int statusCode) {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("submeter um novo restaurante")
    public void submeter_um_novo_restaurante() {
        var restaurantRequest = RestaurantHelper.registerRestaurant();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restaurantRequest)
                .when().post(ENDPOINT_RESTAURANT + "/create");
        //return response.then().extract().as(Restaurant.class);
    }

    @Então("restaurante e registrado com sucesso")
    public void restaurante_e_registrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("./schemas/RestaurantResponseSchema.json"));
    }

    @Dado("que um restaurant já foi publicado")
    public void que_um_resaturant_já_foi_publicado() {
        restaurantResponse = submeterNovoRestaurant();
    }

    @Quando("requisitar a busca de restaurante")
    public void requisitarBuscarRestaurant() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/restaurant/find/documento{document}", restaurantResponse.document());
    }

    @Então("restaurante é exibido com sucesso")
    public void restaurantExibidoComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/RestaurantResponseSchema.json"));
    }

    @Quando("requisitar a lista de restaurantes")
    public void requisitarListaRestaurants() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/restaurant/all");
    }

    @Então("restaurantes são exibidos com sucesso")
    public void restaurantsSaoExibidosComSucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
//                .body(matchesJsonSchemaInClasspath("./schemas/MensagemPaginationSchema.json"))
//                .body("number", equalTo(0))
//                .body("size", equalTo(10));
    }*/

}
