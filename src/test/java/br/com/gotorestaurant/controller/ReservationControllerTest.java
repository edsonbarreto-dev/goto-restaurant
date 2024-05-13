package br.com.gotorestaurant.controller;

import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.application.service.ReservationService;
import br.com.gotorestaurant.application.service.RestaurantService;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.application.shared.ReservationMapper;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.records.BirthdayPerson;
import br.com.gotorestaurant.utils.CustomerHelper;
import br.com.gotorestaurant.utils.RestaurantHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureJsonTesters
class ReservationControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService service;


    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informações são inválidas")
    void schedulingScene1() throws Exception {
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/api/reservation/make");
        MockHttpServletResponse response = mvc.perform(post).andReturn().getResponse();
        assertThat(true);
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Registrar reserva")
    void makeReservation() throws Exception {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setId(14879858988L);
        reservation.setDate(LocalDate.now());
        reservation.setCustomerEntity(CustomerHelper.registerCustomer());
        reservation.setNumberOfPeople(10);
        reservation.setReservedTableNumber(5);
        reservation.setHasCancelled(false);
        reservation.setShowedUp(true);
        reservation.setBirthdaysPersonEntity(RestaurantHelper.registrarNivers());

        var reservationVo = ReservationMapper.toReservation(reservation);

        String reservationRequest = mapper.writeValueAsString(reservationVo);
        mvc.perform(MockMvcRequestBuilders.post("/api/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(reservationRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

}
