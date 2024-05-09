package br.com.gotorestaurant.repository;


import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.utils.RestaurantHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RestaurantRespositoryIT {
    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Test
    void shouldAllowCreateTable(){
        long totalRegistrosCriados = restaurantRepository.count();
        assertThat(totalRegistrosCriados).isZero();
    }
    @Test
    void shouldAllowRegisterRestaurant(){
        long id = RestaurantHelper.geradorId();
        var restaurant = RestaurantHelper.registerRestaurant();
        restaurant.setId(id);

        var newRestaurant = restaurantRepository.save(restaurant);
        assertThat(newRestaurant)
                .isNotNull()
                .isInstanceOf(RestaurantEntity.class);
       // assertThat(newRestaurant.getId()).isEqualTo(restaurant.getId());
        assertThat(newRestaurant.getName()).isEqualTo(restaurant.getName());
        assertThat(newRestaurant.getCapacity()).isEqualTo(restaurant.getCapacity());
        assertThat(newRestaurant.getDocument()).isEqualTo(restaurant.getDocument());
    }

    @Test
    void shouldAllowFindRestaurant(){

        long id = RestaurantHelper.geradorId();
        var restaurant = RestaurantHelper.registerRestaurant();
        restaurant.setId(id);

        var restaurantSave = registrarRestaurant(restaurant);
        var restaurantFound = restaurantRepository.findByDocument(restaurantSave.getDocument());

        //Assert
        assertThat(restaurantFound).isNotNull();
//        assertThat(restaurantFound.getName()).isEqualTo(restaurant.getName());


    }

    @Test
    void shouldAllowDeleteRestaurant(){
        long id = RestaurantHelper.geradorId();

        restaurantRepository.deleteById(id);
        var restaurantFoundOptional = restaurantRepository.findById(id);
        assertThat(restaurantFoundOptional).isEmpty();
    }

    @Test
    void shouldAllowListRestaurant(){

       //act
        var lista = restaurantRepository.findAll();
        //Assert
        assertThat(lista)
                .hasSizeGreaterThanOrEqualTo(0);
    }

    private RestaurantEntity registrarRestaurant(RestaurantEntity restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
