package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.repository.entity.*;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.exceptions.RestaurantNullException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class RestaurantMapper {

    private RestaurantMapper() {
    }

    public static Restaurant toRestaurant(RestaurantVO restaurant) {
        if (restaurant == null) throw new RestaurantNullException();
        return new Restaurant(restaurant.document(), restaurant.name(), restaurant.capacity())
            .setBrand(restaurant.brand())
            .addAddress(restaurant.addresses())
            .setPhones(restaurant.phones())
            .setSocialMedia(restaurant.socialMedia())
            .setEmployees(restaurant.employees())
            .setCustomers(restaurant.customers())
            .setSuppliers(restaurant.suppliers())
            .setPartners(restaurant.partners())
            .setReservations(restaurant.reservations());
    }

    public static RestaurantVO toRestaurantVO(Restaurant restaurant) {
        if (restaurant == null) throw new RestaurantNullException();
        return new RestaurantVO(
            restaurant.document(),
            restaurant.name(),
            restaurant.capacity(),
            restaurant.brand(),
            restaurant.addresses(),
            restaurant.phones(),
            restaurant.socialMedia(),
            restaurant.employees(),
            restaurant.customers(),
            restaurant.suppliers(),
            restaurant.partners(),
            restaurant.reservations()
        );
    }

    public static List<RestaurantVO> toLitRestaurantVO(List<Restaurant> restaurants) {
        if (restaurants == null) throw new RestaurantNullException();
        List<RestaurantVO> restaurantVOS = new ArrayList<>();
        restaurants.forEach( restaurant -> restaurantVOS.add(
            new RestaurantVO(
                restaurant.document(),
                restaurant.name(),
                restaurant.capacity(),
                restaurant.brand(),
                restaurant.addresses(),
                restaurant.phones(),
                restaurant.socialMedia(),
                restaurant.employees(),
                restaurant.customers(),
                restaurant.suppliers(),
                restaurant.partners(),
                restaurant.reservations())
            )
        );
        return restaurantVOS;
    }

    public static Restaurant toRestaurant(RestaurantEntity restaurant) {
        if (restaurant == null) throw new RestaurantNullException();
        return new Restaurant(restaurant.getDocument(), restaurant.getName(), restaurant.getCapacity())
            .setBrand(BrandMapper.toBrand(restaurant.getBrandEntity()))
            .addAddress(AddressMapper.toListAddress(restaurant.getAddressEntity()))
            .setPhones(PhoneMapper.fromListEntityToListCore(restaurant.getPhoneEntity()))
            .setSocialMedia(SocialMediaMapper.toListSocialMedia(restaurant.getSocialMediaEntity()))
            .setEmployees(EmployeerMapper.toListEmployee(restaurant.getEmployeeEntity()))
            .setCustomers(CustomerMapper.toListCustomer(restaurant.getCustomerEntity()))
            .setSuppliers(SupplierMapper.toListSupplier(restaurant.getSupplierEntity()))
            .setPartners(PartnerMapper.toListPartner(restaurant.getPartnerEntity()))
            .setReservations(ReservationMapper.toListReservation(restaurant.getReservationEntity()));
    }

    public static RestaurantEntity toRestaurantEntity(Restaurant restaurant) {
        if (restaurant == null) throw new RestaurantNullException();
        BrandEntity brandEntity = BrandMapper.toBrandEntity(restaurant.brand());
        List<AddressEntity> addressEntity = AddressMapper.toListAddressEntities(restaurant.addresses());
        List<CustomerEntity> listCustomerEntity = CustomerMapper.toListCustomerEntity(restaurant.customers());
        List<SocialMediaEntity> listSocialMedia = SocialMediaMapper.toListSocialMediaEntity(restaurant.socialMedia());
        List<PartnerEntity> listPartners = PartnerMapper.toListPartnerEntity(restaurant.partners());
        List<EmployeeEntity> listEmployees = EmployeerMapper.toListEmployeeEntity(restaurant.employees());
        List<SupplierEntity> listSuppliers = SupplierMapper.toListSupplierEntity(restaurant.suppliers());
        List<ReservationEntity> listReservations = ReservationMapper.toListReservationEntity(restaurant.reservations());

        RestaurantEntity entity = new RestaurantEntity();
        entity.setDocument(restaurant.document());
        entity.setName(restaurant.name());
        entity.setCapacity(restaurant.capacity());
        entity.setBrandEntity(brandEntity);
        entity.setAddressEntity(addressEntity);
        entity.setCustomerEntity(listCustomerEntity);
        entity.setSocialMediaEntity(listSocialMedia);
        entity.setPartnerEntity(listPartners);
        entity.setEmployeeEntity(listEmployees);
        entity.setSupplierEntity(listSuppliers);
        entity.setReservationEntity(listReservations);

        return entity;
    }
}
