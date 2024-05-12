package br.com.gotorestaurant.application.presenter.restaurant;

import br.com.gotorestaurant.application.repository.ICustomerRepository;
import br.com.gotorestaurant.application.repository.IPhoneRepository;
import br.com.gotorestaurant.application.repository.IReservationRepository;
import br.com.gotorestaurant.application.repository.IRestaurantRepository;
import br.com.gotorestaurant.application.repository.entity.*;
import br.com.gotorestaurant.application.shared.*;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.CustomerNotFoundException;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundException;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundForReservationException;
import br.com.gotorestaurant.core.exceptions.RestaurantNotFoundForUpdateCustomerException;
import br.com.gotorestaurant.core.records.Reservation;
import br.com.gotorestaurant.core.usecase.restaurant.interfaces.IRestaurantPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantPresenter implements IRestaurantPresenter {

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Transactional
    @Override
    public Long createRestaurant(Restaurant restaurantEntity) {
        RestaurantEntity add = RestaurantMapper.toRestaurantEntity(restaurantEntity);
        RestaurantEntity entity = this.restaurantRepository.save(add);
        return entity.getId();
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = this.restaurantRepository.findByDocument(restaurant.document())
                .orElseThrow(RestaurantNotFoundException::new);
        restaurantEntity.setCustomers(CustomerMapper.toListCustomerEntity(restaurant.customers()));
        this.restaurantRepository.save(restaurantEntity);
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer, String restaurantDocument) {
        RestaurantEntity restaurantEntity = this.restaurantRepository.findByDocument(restaurantDocument)
                .orElseThrow(RestaurantNotFoundForUpdateCustomerException::new);

        CustomerEntity customerEntity;
        try {
            customerEntity = this.customerRepository.findByDocument(customer.getDocument())
                    .orElseThrow(CustomerNotFoundException::new);

            List<PhoneEntity> phoneEntities = PhoneMapper.fromListCoreToListEntity(customer.getPhones());
            phoneEntities.forEach(phoneEntity -> phoneEntity.setCustomerEntity(customerEntity));
            this.phoneRepository.saveAll(phoneEntities);

            customerEntity.setPhones(phoneEntities);
            customerEntity.setRestaurantFK(List.of(restaurantEntity));

            this.customerRepository.updateCustomer(customerEntity);

            restaurantEntity.setCustomers(List.of(customerEntity));

            this.restaurantRepository.updateRestaurant(restaurantEntity);
        } catch (CustomerNotFoundException e) {
            CustomerEntity ce = CustomerMapper.toCustomerEntity(customer);
            ce.setRestaurantFK(List.of(restaurantEntity));
            this.customerRepository.save(CustomerMapper.toCustomerEntity(customer));
        }
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        RestaurantEntity restaurantEntity = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);
        return RestaurantMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public Restaurant findByDocument(String document) {
        RestaurantEntity restaurantEntity = this.restaurantRepository.findByDocument(document)
                .orElseThrow(RestaurantNotFoundException::new);
        return RestaurantMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        Iterable<RestaurantEntity> all = this.restaurantRepository.findAll();
        all.forEach(r -> restaurants.add(RestaurantMapper.toRestaurant(r)));
        return restaurants;
    }

    @Override
    public List<Restaurant> findByName(String name) {
        return new ArrayList<>();
    }

    @Override
    public List<Restaurant> findByCapacity(int capacity) {
        return new ArrayList<>();
    }

    @Override
    public List<Restaurant> findByReservationExists() {
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public void makeReservation(Reservation reservation, String documentRestaurant) {
        RestaurantEntity restaurantEntity = this.restaurantRepository.findByDocument(documentRestaurant)
                .orElseThrow(RestaurantNotFoundForReservationException::new);

        CustomerEntity customerSaved;
        try {
            customerSaved = this.customerRepository.findByDocument(reservation.customer().getDocument())
                    .orElseThrow(CustomerNotFoundException::new);
        } catch (CustomerNotFoundException e) {
            customerSaved = this.customerRepository.save(CustomerMapper.toCustomerEntity(reservation.customer()));
        }

        ReservationEntity reservationEntity = ReservationMapper.toReservationEntity(reservation);
        reservationEntity.setCustomerEntity(customerSaved);
        reservationEntity.setRestaurantEntity(restaurantEntity);

        List<BirthdayPersonEntity> listBirthdayPersonEntity = BirthdayPersonMapper.toListBirthdayPersonEntity(reservation.birthdays());
        listBirthdayPersonEntity.forEach(birthdayPersonEntity -> birthdayPersonEntity.setReservationEntity(reservationEntity));
        reservationEntity.setBirthdaysPersonEntity(listBirthdayPersonEntity);

        ReservationEntity reservationSaved = this.reservationRepository.save(reservationEntity);

        List<ReservationEntity> reservations = restaurantEntity.getReservations();
        reservations.add(reservationSaved);

        restaurantEntity.setReservations(reservations);

        this.restaurantRepository.makeReservation(restaurantEntity);
    }
}
