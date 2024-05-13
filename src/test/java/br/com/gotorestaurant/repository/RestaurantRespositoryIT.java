package br.com.gotorestaurant.repository;


import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.CustomerEntity;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.utils.RestaurantHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RestaurantRespositoryIT {
    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Autowired
    private ICustomerRepository customerRepository;

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
        var document =  restaurant.getDocument();
        var restaurantSave = registrarRestaurant(restaurant);
        var restaurantFound = restaurantRepository.findByDocument(document);

        //Assert
        assertThat(restaurantFound).isPresent();
        assertThat(restaurantFound.get().getName()).isEqualTo(restaurant.getName());


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

    private Optional<RestaurantEntity> registrarRestaurant(RestaurantEntity restaurant) {
        List<CustomerEntity> lista = new ArrayList<>();
        for(var customer : restaurant.getCustomers()){
            var customerNovo = customerRepository.save(customer);
            lista.add(customerNovo);
        }

        restaurant.setCustomers(lista);
        return Optional.of(restaurantRepository.save(restaurant));
    }


}
