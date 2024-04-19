package br.com.gotorestaurant.application.record;

import br.com.gotorestaurant.core.records.*;

import java.util.List;

public record RestaurantVO(
        String document,
        String name,
        Integer capacity,
        Brand brand,
        Address address,
        List<Phone> phones,
        List<SocialMedia> socialMedia,
        List<Employee> employees,
        List<Customer> customers,
        List<Supplier> suppliers,
        List<Partner> partners,
        List<Reservation> reservations
) {
}
