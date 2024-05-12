package br.com.gotorestaurant.repository;

import br.com.gotorestaurant.application.repository.IReservationRepository;
import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.utils.ReservationHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
@AutoConfigureTestDatabase
@SpringBootTest
@Transactional
class ReservationRepositoryTestIT {

    @Autowired
    private IReservationRepository reservationRepository;

    @Test
    void shouldAllowFindByDate() {
        // Arrange
        var reserve = ReservationHelper.registrarReserva();
        reserve.getRestaurantEntity().setId(100L);
        reserve.getCustomerEntity().setId(1L);
        var reserveAdd = cadastrar(reserve);
        // Act
        var reserveOptional = reservationRepository.findByDate(reserve.getDate());
        // Assert
        assertThat(reserveOptional)
                .isPresent()
                .containsSame(reserve);
        reserveOptional.ifPresent(newReserve -> {
            assertThat(newReserve.getId())
                    .isEqualTo(reserve.getId());
            assertThat(newReserve.getDate())
                    .isEqualTo(reserve.getDate());
            assertThat(newReserve.getReservedTableNumber())
                    .isEqualTo(reserve.getReservedTableNumber());
            assertThat(newReserve.getNumberOfPeople())
                    .isEqualTo(reserve.getNumberOfPeople());
        });
    }

    private ReservationEntity cadastrar(ReservationEntity reserve) {
        return reservationRepository.save(reserve);
    }
}
