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

    public static Restaurant toRestaurantEntity(RestaurantVO restaurant) {
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

    public static Restaurant toRestaurantEntity(RestaurantEntity restaurant) {
        return new Restaurant(restaurant.getDocument(), restaurant.getName(), restaurant.getCapacity())
            .setBrand(BrandMapper.toBrand(restaurant.getBrandEntityJPA()))
            .addAddress(AddressMapper.toListAddressEntities(restaurant.getAddressEntityJPA()))
            .setPhones(PhoneMapper.toListPhone(restaurant.getPhoneEntityJPA()))
            .setSocialMedia(SocialMediaMapper.toListSocialMedia(restaurant.getSocialMediaEntityJPA()))
            .setEmployees(EmployeerMapper.toListEmployee(restaurant.getEmployeeEntityJPA()))
            .setCustomers(CustomerMapper.toListCustomer(restaurant.getCustomerEntityJPA()))
            .setSuppliers(SupplierMapper.toListSupplier(restaurant.getSupplierEntityJPA()))
            .setPartners(PartnerMapper.toListPartner(restaurant.getPartnerEntityJPA()))
            .setReservations(ReservationMapper.toListReservation(restaurant.getReservationEntityJPA()));
    }

    public static RestaurantEntity toRestaurantEntityJPA(Restaurant restaurantEntity) {
        BrandEntity brandEntity = BrandMapper.toBrandEntity(restaurantEntity.brand());
        List<AddressEntity> addressEntity = AddressMapper.toListAddressEntitiesJPA(restaurantEntity.address());
        List<CustomerEntity> listCustomerEntity = CustomerMapper.toListCustomerEntity(restaurantEntity.customers());
        List<SocialMediaEntity> listSocialMedia = SocialMediaMapper.toListSocialMediaEntity(restaurantEntity.socialMedia());
        List<PartnerEntity> listPartners = PartnerMapper.toListPartnerEntity(restaurantEntity.partners());
        List<EmployeeEntity> listEmployees = EmployeerMapper.toListEmployeeEntity(restaurantEntity.employees());
        List<SupplierEntity> listSuppliers = SupplierMapper.toListSupplierEntity(restaurantEntity.suppliers());
        List<ReservationEntity> listReservations = ReservationMapper.toListReservationEntity(restaurantEntity.reservations());

        RestaurantEntity restaurantEntityJPA = new RestaurantEntity();
        restaurantEntityJPA.setDocument(restaurantEntity.document());
        restaurantEntityJPA.setName(restaurantEntity.name());
        restaurantEntityJPA.setCapacity(restaurantEntity.capacity());
        restaurantEntityJPA.setBrandEntityJPA(brandEntity);
        restaurantEntityJPA.setAddressEntityJPA(addressEntity);
        restaurantEntityJPA.setCustomerEntityJPA(listCustomerEntity);
        restaurantEntityJPA.setSocialMediaEntityJPA(listSocialMedia);
        restaurantEntityJPA.setPartnerEntityJPA(listPartners);
        restaurantEntityJPA.setEmployeeEntityJPA(listEmployees);
        restaurantEntityJPA.setSupplierEntityJPA(listSuppliers);
        restaurantEntityJPA.setReservationEntityJPA(listReservations);

        return restaurantEntityJPA;
    }
}
