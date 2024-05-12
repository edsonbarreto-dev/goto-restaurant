package br.com.gotorestaurant.core.usecase.restaurant.implementation.update;

import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.records.Supplier;

public class AddSupplierRestaurantUseCase {

    public AddSupplierRestaurantUseCase(Supplier supplier, Restaurant restaurantEntity) {
        restaurantEntity.addSupplier(supplier);
    }
}
