package br.com.gotorestaurant.service;

import br.com.gotorestaurant.application.presenter.restaurant.RestaurantPresenter;
import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.application.service.RestaurantService;
import br.com.gotorestaurant.application.shared.RestaurantMapper;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.RestaurantHasExistsException;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundException;
import br.com.gotorestaurant.core.usecase.restaurant.implementation.create.CreateRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.implementation.read.FindRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IListRestaurantUseCase;
import br.com.gotorestaurant.utils.RestaurantHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private CreateRestaurantUseCase createRestaurantUseCase;

    @Mock
    private FindRestaurantUseCase findRestaurantUseCase;

    @Mock
    private IListRestaurantUseCase listRestaurantUseCase;

    @Mock
    private RestaurantPresenter restaurantPresenter;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        restaurantService = new RestaurantService(restaurantRepository, createRestaurantUseCase,
                findRestaurantUseCase, listRestaurantUseCase);
       // restaurantService = new RestaurantService(new CreateRestaurantUseCase(findRestaurantUseCase, restaurantRepository));
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldAllowRegisterRestaurant() {
        var restaurant = RestaurantHelper.cadastrarRestaurante();

        //var restaurantEntity = RestaurantHelper.registerRestaurant();
        when(createRestaurantUseCase.createRestaurant(any(Restaurant.class)))
                .thenAnswer(i -> 1L);

        System.out.println(restaurant.name());
        var restaurantCadastrado = restaurantService.create(restaurant);

        assertThat(restaurantCadastrado).isNotNull();
        verify(createRestaurantUseCase, times(1)).createRestaurant(any(Restaurant.class));
    }

    @Test
    void shouldAllowRegisterRestaurant_WhenRestaurantExist() {
        var restaurant = RestaurantHelper.cadastrarRestaurante();

        when(createRestaurantUseCase.createRestaurant(any(Restaurant.class)))
                .thenThrow(RestaurantHasExistsException.class);

        assertThatThrownBy(() -> restaurantService.create(restaurant))
                .isInstanceOf(RestaurantHasExistsException.class);

        verify(createRestaurantUseCase, times(1)).createRestaurant(any(Restaurant.class));
    }

    @Test
    void shouldFindRestaurant(){
        //Arrange
        var id = RestaurantHelper.geradorId();
        var restaurant = RestaurantHelper.cadastrarRestaurante();

        when(findRestaurantUseCase.findBy(id))
                .thenReturn(restaurant);
        //Act
        var newRestaurant = restaurantService.findBy(id);
        //Assert
        assertThat(newRestaurant).isEqualTo(restaurant);
        verify(findRestaurantUseCase, times(1))
                .findBy(any(Long.class));
    }

    @Test
    void deveGerarExcecao_QuandoBuscaRestaurant_IdNaoExiste(){
        //Arrange
        var id = RestaurantHelper.geradorId();

        when(findRestaurantUseCase.findBy(any(Long.class)))
                .thenThrow(new RestaurantNotFoundException());
        //Assert
        assertThatThrownBy(() -> restaurantService.findBy(id))
                .isInstanceOf(RestaurantNotFoundException.class)
                .hasMessage("Não localizei o restaurante com o documento informado.");
        verify(findRestaurantUseCase, times(1))
                .findBy(id);
    }


    @Test
    void shouldAllowListRestaurant(){

        List<Restaurant> listaRestaurants = Arrays.asList(
                RestaurantHelper.cadastrarRestaurante(),
                RestaurantHelper.cadastrarRestaurante()
        );
        when(listRestaurantUseCase.listAll())
                .thenReturn(listaRestaurants);

        //act
        var result = restaurantService.listAll();
        //assert
        assertThat(result).hasSize(2);

        verify(listRestaurantUseCase, times(1)).listAll();

    }
}