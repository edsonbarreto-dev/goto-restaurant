
package br.com.gotorestaurant.bdd;

import br.com.gotorestaurant.infra.security.TokenService;
import br.com.gotorestaurant.infra.user.User;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.mockito.Mockito;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class CucumberTest {

    private static TokenService tokenService;

    @BeforeAll
    public static void setup() {
//        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.baseURI = "https://gotorestaurant.azurewebsites.net";

        tokenService = Mockito.mock(TokenService.class);
        Mockito.when(tokenService.generateToken(new User(1L,"fiap","123456")))
                .thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVm9sbC5tZWQiLCJzdWIiOiJmaWFwIiwiaWQiOjEsImV4cCI6MTcxNTU3Mjk2NH0.RCL9sRkpDPTwoJBPxjnVP16JfmEhWHwzObJhcUpO2qg");
    }

    public static TokenService getTokenService() {
        return tokenService;
    }
}
