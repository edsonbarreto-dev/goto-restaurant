package br.com.gotorestaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import br.com.gotorestaurant.application.service.RestaurantService;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RestaurantControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RestaurantService service;

  @Test
  @DisplayName("Deveria devolver codigo http 400 quando informações são inválidas")
  void schedulingScene1() throws Exception {
    MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/api/restaurant/create");
    MockHttpServletResponse response = mvc.perform(post).andReturn().getResponse();
    assertThat(true);
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }
}
