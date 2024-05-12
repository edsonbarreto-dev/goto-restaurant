package br.com.gotorestaurant.service;

import br.com.gotorestaurant.application.presenter.restaurant.CustomerPresenter;
import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IReservationRepository;
import br.com.gotorestaurant.application.service.CustomerService;
import br.com.gotorestaurant.application.service.ReservationService;
import br.com.gotorestaurant.application.shared.CustomerMapper;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.ICustomerService;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IReservationService;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.ICreateCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.create.IMakeReservationUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindCustomerUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindReservationUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.read.IFindRestaurantUseCase;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.update.IUpdateCustomerUseCase;
import br.com.gotorestaurant.utils.CustomerHelper;
import br.com.gotorestaurant.utils.ReservationHelper;
import br.com.gotorestaurant.utils.RestaurantHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservationServiceTest {
    private IReservationService reservationService;


    @Mock
    private IMakeReservationUseCase makeReservationUseCase;

    @Mock
    private IFindReservationUseCase findReservationUseCase;

    @Mock
    private IFindRestaurantUseCase findRestaurantUseCase;
    @Mock
    private IReservationRepository reservationRepository;
    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        reservationService = new ReservationService(reservationRepository, makeReservationUseCase,
                findReservationUseCase, findRestaurantUseCase);
        // restaurantService = new RestaurantService(new CreateRestaurantUseCase(findRestaurantUseCase, restaurantRepository));
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Nested
    class RegisterReserve {
        @Test
        void shouldAllowMakeReservartion() {
            var reserve = ReservationHelper.addReserva();
            var restaurant = RestaurantHelper.cadastrarRestaurante();
            restaurant.setCustomers(CustomerHelper.customers());

            reservationService.makeReservation(reserve, restaurant.document());

            assertThat(reserve.numberOfPeople()).isEqualTo(10);
            verify(makeReservationUseCase, times(1)).
                    makeReservation(any(Reservation.class), any(Restaurant.class));
        }

    }
}
