package br.com.gotorestaurant.repository;

import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IReservationRepository;
import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.application.repository.entity.RestaurantEntity;
import br.com.gotorestaurant.utils.CustomerHelper;
import br.com.gotorestaurant.utils.ReservationHelper;
import br.com.gotorestaurant.utils.RestaurantHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationRepositoryTest {

    @Mock
    private IReservationRepository reservationRepository;
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
    void findByDateAndReservedTableNumber(){
        var reserve = ReservationHelper.registrarReserva();

        when(reservationRepository.findByDateAndReservedTableNumber(any(LocalDate.class), any(Integer.class)))
                .thenReturn(reserve);

        //Act
        var newReserve = reservationRepository.findByDateAndReservedTableNumber(reserve.getDate(), reserve.getReservedTableNumber());
        //Assert
        assertThat(newReserve)
                .isNotNull()
                .isInstanceOf(ReservationEntity.class);
        assertThat(newReserve.getId()).isEqualTo(reserve.getId());
        assertThat(newReserve.getDate()).isEqualTo(reserve.getDate());

        verify(reservationRepository, times(1))
                .findByDateAndReservedTableNumber(any(LocalDate.class), any(Integer.class));
    }

    @Test
    void findByDate(){
        var reserve = ReservationHelper.registrarReserva();

        when(reservationRepository.findByDate(any(LocalDate.class)))
                .thenReturn(Optional.of(reserve));

        //Act
        var newReserve = reservationRepository.findByDate(reserve.getDate());
        //Assert
        assertThat(newReserve)
                .isPresent();
        assertThat(newReserve.get().getId()).isEqualTo(reserve.getId());

        verify(reservationRepository, times(1))
                .findByDate(any(LocalDate.class));
    }

    @Test
    void findByCustomerDocument(){
        var reserve = ReservationHelper.registrarReserva();

        when(reservationRepository.findByCustomerDocument(any(String.class)))
                .thenReturn(reserve);

        //Act
        var newReserve = reservationRepository.findByCustomerDocument(reserve.getCustomerEntity().getDocument());
        //Assert
        assertThat(newReserve)
                .isNotNull()
                .isInstanceOf(ReservationEntity.class);
        assertThat(newReserve.getId()).isEqualTo(reserve.getId());

        verify(reservationRepository, times(1))
                .findByCustomerDocument(any(String.class));
    }

    @Test
    void findByCustomerDocumentAndRestaurantDocumentAndDate(){
        var reserve = ReservationHelper.registrarReserva();

        when(reservationRepository.findByCustomerDocumentAndRestaurantDocumentAndDate(any(String.class),any(String.class),
                any(LocalDate.class)))
                .thenReturn(Optional.of(reserve));

        //Act
        var newReserve = reservationRepository.findByCustomerDocumentAndRestaurantDocumentAndDate(reserve.getCustomerEntity().getDocument(),
                                    reserve.getRestaurantEntity().getDocument(), reserve.getDate());
        //Assert
        assertThat(newReserve)
                .isPresent();
        assertThat(newReserve.get().getId()).isEqualTo(reserve.getId());

        verify(reservationRepository, times(1))
                .findByCustomerDocumentAndRestaurantDocumentAndDate(any(String.class),any(String.class),
                any(LocalDate.class));
    }

    @Test
    void shouldAllowMakeReservation(){

        var reserve = ReservationHelper.registrarReserva();

        doNothing().when(reservationRepository).makeReservation(any(RestaurantEntity.class));

        reservationRepository.makeReservation(reserve.getRestaurantEntity());

        verify(reservationRepository, times(1))
                .makeReservation(any(RestaurantEntity.class));
    }


}
