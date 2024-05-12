package br.com.gotorestaurant.repository;

import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.utils.RestaurantHelper;
import com.redis.S;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantRespositoryTest {
    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private ICustomerRepository customerRepository;
    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }
    @Test
    void shouldAllowRegisterRestaurant(){
        long id = 1;
        var restaurant = RestaurantHelper.registerRestaurant();
        restaurant.setId(id);
        when(restaurantRepository.save(any(RestaurantEntity.class))).thenReturn(restaurant);
        var newRestaurant = restaurantRepository.save(restaurant);
        assertThat(newRestaurant)
                .isNotNull()
                .isEqualTo(restaurant);
        verify(restaurantRepository, times(1)).save(any(RestaurantEntity.class));
    }

    @Test
    void shouldAllowFindRestaurant(){
        long id = RestaurantHelper.geradorId();
        var restaurant = RestaurantHelper.registerRestaurant();
        restaurant.setId(id);

        when(restaurantRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(restaurant));

        //Act
        var restaurantFoundOptional = restaurantRepository.findById(id);
        //Assert
        assertThat(restaurantFoundOptional)
                .isPresent()
                .containsSame(restaurant);
        restaurantFoundOptional.ifPresent(restaurantFounded -> {
            assertThat(restaurantFounded.getId()).isEqualTo(restaurant.getId());
            assertThat(restaurantFounded.getName()).isEqualTo(restaurant.getName());
        });
        verify(restaurantRepository, times(1))
                .findById(any(Long.class));
    }

    @Test
    void shouldAllowFindRestaurantByDocument(){
        long id = RestaurantHelper.geradorId();
        var restaurant = RestaurantHelper.registerRestaurant();
        restaurant.setId(id);

        when(restaurantRepository.findByDocument(any(String.class)))
                .thenReturn(Optional.of(restaurant));

        //Act
        var restaurantFoundOptional = restaurantRepository.findByDocument(restaurant.getDocument());
        //Assert
        assertThat(restaurantFoundOptional)
                .isPresent()
                .containsSame(restaurant);
        restaurantFoundOptional.ifPresent(restaurantFounded -> {
            assertThat(restaurantFounded.getId()).isEqualTo(restaurant.getId());
            assertThat(restaurantFounded.getName()).isEqualTo(restaurant.getName());
        });
        verify(restaurantRepository, times(1))
                .findByDocument(any(String.class));
    }

    @Test
    void shouldAllowDeleteRestaurant(){
        long id = RestaurantHelper.geradorId();
        var restaurant = RestaurantHelper.registerRestaurant();
        restaurant.setId(id);

        doNothing().when(restaurantRepository).deleteById(any(Long.class));
        restaurantRepository.deleteById(id);
        verify(restaurantRepository, times(1)).deleteById(any(Long.class));
    }

    @Test
    void shouldAllowListRestaurant(){

        var restaurant1 = RestaurantHelper.registerRestaurant();
        var restaurant2 = RestaurantHelper.registerRestaurant();
        var listRestaurant = Arrays.asList(restaurant1,restaurant2);

       when(restaurantRepository.findAll()).thenReturn(listRestaurant);

       //act
        var lista = restaurantRepository.findAll();
        //Assert
        assertThat(lista)
                .hasSize(2)
                .containsExactlyInAnyOrder(restaurant1, restaurant2);

        verify(restaurantRepository, times(1))
                .findAll();

    }

    @Test
    void shouldAllowUpdateRestaurant(){

        var newRestaurant  = RestaurantHelper.registerRestaurant();
        newRestaurant.setCapacity(500);

        doNothing().when(restaurantRepository).updateRestaurant(newRestaurant);

        restaurantRepository.updateRestaurant(newRestaurant);

        verify(restaurantRepository, times(1))
                .updateRestaurant(any(RestaurantEntity.class));
    }
    @Test
    void shouldAllowRegisterReservationRestaurant(){

        var newRestaurant  = RestaurantHelper.registerRestaurant();

        doNothing().when(restaurantRepository).makeReservation(newRestaurant);

        restaurantRepository.makeReservation(newRestaurant);

        verify(restaurantRepository, times(1))
                .makeReservation(any(RestaurantEntity.class));
    }
}
