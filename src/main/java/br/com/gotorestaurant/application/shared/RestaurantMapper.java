package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.record.RestaurantVO;
import br.com.gotorestaurant.application.repository.entity.*;
import br.com.gotorestaurant.core.entity.RestaurantEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class RestaurantMapper {

    private RestaurantMapper() {
    }

    public static RestaurantEntity toRestaurantEntity(RestaurantVO restaurant) {
        return new RestaurantEntity(restaurant.document(), restaurant.name(), restaurant.capacity())
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

    public static RestaurantEntity toRestaurantEntity(RestaurantEntityJPA restaurant) {
        return new RestaurantEntity(restaurant.getDocument(), restaurant.getName(), restaurant.getCapacity())
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

    public static RestaurantEntityJPA toRestaurantEntityJPA(RestaurantEntity restaurantEntity) {
        BrandEntityJPA brandEntityJPA = BrandMapper.toBrandEntity(restaurantEntity.brand());
        List<AddressEntityJPA> addressEntityJPA = AddressMapper.toListAddressEntitiesJPA(restaurantEntity.address());
        List<CustomerEntityJPA> listCustomerEntity = CustomerMapper.toListCustomerEntity(restaurantEntity.customers());
        List<SocialMediaEntityJPA> listSocialMedia = SocialMediaMapper.toListSocialMediaEntity(restaurantEntity.socialMedia());
        List<PartnerEntityJPA> listPartners = PartnerMapper.toListPartnerEntity(restaurantEntity.partners());
        List<EmployeeEntityJPA> listEmployees = EmployeerMapper.toListEmployeeEntity(restaurantEntity.employees());
        List<SupplierEntityJPA> listSuppliers = SupplierMapper.toListSupplierEntity(restaurantEntity.suppliers());
        List<ReservationEntityJPA> listReservations = ReservationMapper.toListReservationEntity(restaurantEntity.reservations());

        RestaurantEntityJPA restaurantEntityJPA = new RestaurantEntityJPA();
        restaurantEntityJPA.setDocument(restaurantEntity.document());
        restaurantEntityJPA.setName(restaurantEntity.name());
        restaurantEntityJPA.setCapacity(restaurantEntity.capacity());
        restaurantEntityJPA.setBrandEntityJPA(brandEntityJPA);
        restaurantEntityJPA.setAddressEntityJPA(addressEntityJPA);
        restaurantEntityJPA.setCustomerEntityJPA(listCustomerEntity);
        restaurantEntityJPA.setSocialMediaEntityJPA(listSocialMedia);
        restaurantEntityJPA.setPartnerEntityJPA(listPartners);
        restaurantEntityJPA.setEmployeeEntityJPA(listEmployees);
        restaurantEntityJPA.setSupplierEntityJPA(listSuppliers);
        restaurantEntityJPA.setReservationEntityJPA(listReservations);

        return restaurantEntityJPA;
    }
}
