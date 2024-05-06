package br.com.gotorestaurant.application.presenter.reservation;

import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IPhoneRepository;
import br.com.gotorestaurant.application.repository.IReservationRepository;
import br.com.gotorestaurant.application.repository.ISocialMediaRepository;
import br.com.gotorestaurant.application.repository.entity.ReservationEntity;
import br.com.gotorestaurant.application.shared.ReservationMapper;
import br.com.gotorestaurant.core.exceptions.ReservationNotFoundForCustomerDocument;
import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IReservationPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class ReservationPresenter implements IReservationPresenter {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Autowired
    private ISocialMediaRepository socialMediaRepository;

    @Autowired
    private IReservationRepository reservationRepository;

    @Transactional
    @Override
    public Long makeReservation(Reservation reservation) {
        return 0L;
    }

    @Transactional
    @Override
    public void updateReservation(Reservation reservation) {
    }

    @Override
    public Reservation findById(Long id) {
        return null;
    }

    @Override
    public Reservation findByCustomerDocument(String document) {
        ReservationEntity result = this.reservationRepository.findByCustomerDocument(document);
        return ReservationMapper.toReservation(result);
    }

    @Override
    public boolean isTableReservedInDate(LocalDate date, int reservedTableNumber) {
        ReservationEntity result = this.reservationRepository.findByDateAndReservedTableNumber(date, reservedTableNumber);
        return result != null;
    }

    @Override
    public Reservation findByDocumentAndRestaurantDocumentAndDate(String document, String restaurantDocument, LocalDate date) {
        ReservationEntity result = this.reservationRepository
                .findByCustomerDocumentAndRestaurantDocumentAndDate(document, restaurantDocument, date)
                .orElseThrow(ReservationNotFoundForCustomerDocument::new);
        return ReservationMapper.toReservation(result);
    }
}
