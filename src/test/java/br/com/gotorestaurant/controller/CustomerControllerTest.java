package br.com.gotorestaurant.controller;

import br.com.gotorestaurant.application.controller.CustomerController;
import br.com.gotorestaurant.application.service.CustomerService;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.exceptions.CustomerNotFoundException;
import br.com.gotorestaurant.utils.CustomerHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerController controller;

    @MockBean
    private CustomerService service;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informações são inválidas")
    void shouldAllowRegisterRestaurantWhenExist() throws Exception {
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/api/customer");
        MockHttpServletResponse response = mvc.perform(post).andReturn().getResponse();
        assertThat(true);
//    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
    @Nested
    class FindCustomer {
        @Test
        @WithMockUser
        void shouldAllowFindCustomer_ByDocument() throws Exception {
            var id = CustomerHelper.geradorId();
            var customer = CustomerHelper.registerCustomer();
            var document = "783789443";

            when(service.findByDocument(document)).thenReturn(any(Customer.class));

            mvc.perform(get("/api/customer/find/document/{document}", document)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());


            verify(service, times(1)).findByDocument(any(String.class));
        }
    }

    @Test
    @WithMockUser
    void deveGerarExcecao_QuandoBuscarRestaurant_DocumentNaoExistente()
            throws Exception {
        var document = "1273839";
        when(service.findByDocument(document))
                .thenThrow(new CustomerNotFoundException());

        mvc.perform(get("/api/customer/find/document/{document}", document)
                        .contentType(MediaType.APPLICATION_JSON))
//          .andDo(print())
                .andExpect(status().isNotFound());
        verify(service, times(1))
                .findByDocument(any(String.class));
    }

}



