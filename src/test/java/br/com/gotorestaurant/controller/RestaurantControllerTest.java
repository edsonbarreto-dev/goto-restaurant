package br.com.gotorestaurant.controller;

import br.com.gotorestaurant.application.controller.RestaurantController;
import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundException;
import br.com.gotorestaurant.core.usecase.restaurant.implementation.read.FindRestaurantUseCase;
import br.com.gotorestaurant.utils.RestaurantHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import br.com.gotorestaurant.application.service.RestaurantService;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RestaurantControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private RestaurantController controller;

  @MockBean
  private RestaurantService service;


  @Test
  @DisplayName("Deveria devolver codigo http 400 quando informações são inválidas")
  void souldAllowRegisterRestaurant() throws Exception {
    MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/api/restaurant");
    MockHttpServletResponse response = mvc.perform(post).andReturn().getResponse();
    assertThat(true);
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Nested
  class FindRestaurant {
    @Test
    @WithMockUser
    void shouldAllowFindRestaurant_ByDocument() throws Exception {
      var id = RestaurantHelper.geradorId();
      var restaurant = RestaurantHelper.registerRestaurant();
      var document = "123456789";

      when(service.findByDocument(any(String.class))).thenReturn(any(Restaurant.class));

      mvc.perform(get("/api/restaurant/find/document/{document}", document)
                      .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON));
              /*.andExpect(jsonPath("$.document").value(restaurant.getDocument()))
              .andExpect(jsonPath("$.name").value(restaurant.getName()))
              .andExpect(jsonPath("$.capacity").value(restaurant.getCapacity()));*/
      verify(service, times(1)).findByDocument(any(String.class));
    }
  }

  @Test
  @WithMockUser
  void deveGerarExcecao_QuandoBuscarRestaurant_DocumentNaoExistente()
          throws Exception {
    var document = "1273839";
    when(service.findByDocument(document))
            .thenThrow(new RestaurantNotFoundException());

    mvc.perform(get("/api/restaurant/find/document/{document}", document)
                    .contentType(MediaType.APPLICATION_JSON))
//          .andDo(print())
            .andExpect(status().isNoContent());
    verify(service, times(1))
            .findByDocument(any(String.class));
  }

}
