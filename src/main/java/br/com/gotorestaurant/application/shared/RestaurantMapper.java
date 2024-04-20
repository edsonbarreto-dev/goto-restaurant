package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.repository.entity.*;
import br.com.gotorestaurant.core.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class RestaurantMapper {

    private RestaurantMapper() {
    }

    public static Restaurant toRestaurant(RestaurantVO restaurant) {
        return new Restaurant(restaurant.document(), restaurant.name(), restaurant.capacity())
            .setBrand(restaurant.brand())
            .setAddress(restaurant.address())
            .setPhones(restaurant.phones())
            .setSocialMedia(restaurant.socialMedia())
            .setEmployees(restaurant.employees())
            .setCustomers(restaurant.customers())
            .setSuppliers(restaurant.suppliers())
            .setPartners(restaurant.partners())
            .setReservations(restaurant.reservations());
    }

    public static Restaurant toRestaurant(RestaurantEntity restaurant) {
        return new Restaurant(restaurant.getDocument(), restaurant.getName(), restaurant.getCapacity())
            .setBrand(BrandMapper.toBrand(restaurant.getBrandEntity()))
            .addAddress(AddressMapper.toListAddress(restaurant.getAddressEntity()))
            .setPhones(PhoneMapper.toListPhone(restaurant.getPhoneEntity()))
            .setSocialMedia(SocialMediaMapper.toListSocialMedia(restaurant.getSocialMediaEntity()))
            .setEmployees(EmployeerMapper.toListEmployee(restaurant.getEmployeeEntity()))
            .setCustomers(CustomerMapper.toListCustomer(restaurant.getCustomerEntity()))
            .setSuppliers(SupplierMapper.toListSupplier(restaurant.getSupplierEntity()))
            .setPartners(PartnerMapper.toListPartner(restaurant.getPartnerEntity()))
            .setReservations(ReservationMapper.toListReservation(restaurant.getReservationEntity()));
    }

    public static RestaurantEntity toRestaurantEntity(Restaurant restaurantEntity) {
        BrandEntity brandEntity = BrandMapper.toBrandEntity(restaurantEntity.brand());
        List<AddressEntity> addressEntity = AddressMapper.toListAddressEntities(restaurantEntity.address());
        List<CustomerEntity> listCustomerEntity = CustomerMapper.toListCustomerEntity(restaurantEntity.customers());
        List<SocialMediaEntity> listSocialMedia = SocialMediaMapper.toListSocialMediaEntity(restaurantEntity.socialMedia());
        List<PartnerEntity> listPartners = PartnerMapper.toListPartnerEntity(restaurantEntity.partners());
        List<EmployeeEntity> listEmployees = EmployeerMapper.toListEmployeeEntity(restaurantEntity.employees());
        List<SupplierEntity> listSuppliers = SupplierMapper.toListSupplierEntity(restaurantEntity.suppliers());
        List<ReservationEntity> listReservations = ReservationMapper.toListReservationEntity(restaurantEntity.reservations());

        RestaurantEntity entity = new RestaurantEntity();
        entity.setDocument(restaurantEntity.document());
        entity.setName(restaurantEntity.name());
        entity.setCapacity(restaurantEntity.capacity());
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
