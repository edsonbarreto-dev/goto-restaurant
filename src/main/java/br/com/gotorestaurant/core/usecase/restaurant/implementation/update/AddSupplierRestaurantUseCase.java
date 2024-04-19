package br.com.gotorestaurant.core.usecase.restaurant.implementation.update;

import br.com.gotorestaurant.core.entity.RestaurantEntity;
import br.com.gotorestaurant.core.records.Supplier;

public class AddSupplierRestaurantUseCase {

    public AddSupplierRestaurantUseCase(Supplier supplier, RestaurantEntity restaurantEntity) {
        restaurantEntity.addSupplier(supplier);
    }
}
