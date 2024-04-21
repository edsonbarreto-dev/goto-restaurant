package br.com.gotorestaurant.application.record;

import br.com.gotorestaurant.core.records.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record RestaurantVO(
        @NotNull(message = "O número do documento é obrigatório")
        String document,

        @NotNull(message = "O nome do restaurante é obrigatório")
        String name,

        @NotNull(message = "Informe a quantidade máxima de pessoas permitida no estabelecimento.")
        @Positive
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
