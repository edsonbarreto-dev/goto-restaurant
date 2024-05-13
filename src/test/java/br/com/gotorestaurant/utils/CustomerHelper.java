package br.com.gotorestaurant.utils;

import br.com.gotorestaurant.application.repository.entity.*;
import br.com.gotorestaurant.core.entity.Customer;
import br.com.gotorestaurant.core.enums.CountryCodeEnum;
import br.com.gotorestaurant.core.records.Phone;
import br.com.gotorestaurant.core.records.SocialMedia;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CustomerHelper {

    public static CustomerEntity registerCustomer() {
        Long id = geradorId();
        return CustomerEntity.builder()
                .document(UUID.randomUUID().toString())
                .name("Cliente1" + id)
                .email("teset"+ id +"@teset.com")
                .phones(registerPhone())
                .socialMedia(registerSocialMedia())
                .reservations(registrarReservas())
                .build();
    }

    public static Customer addCustomer() {
        Customer customer = new Customer("357951","Cliente1", "teset@teset.com");
        customer.addPhone(new Phone(CountryCodeEnum.BRAZIL,"11",847494849L));
        customer.addSocialMedia(new SocialMedia("teste", "testeAccount", "URL", null, null));
        return customer;
    }

    public static List<Customer> customers(){
        List<Customer> lista = List.of(addCustomer());
        return lista;
    }
    public static List<PhoneEntity> registerPhone(){
        PhoneEntity phone = PhoneEntity.builder()
                .countryCode(CountryCodeEnum.BRAZIL)
                .codeArea("11")
                .number(854874585L)
                .build();
        List<PhoneEntity> lista = new ArrayList<>();
        lista.add(phone);
        return lista;
    }


    public static List<SocialMediaEntity> registerSocialMedia(){
        SocialMediaEntity social = SocialMediaEntity.builder()
                .name("instagran")
                .accountName("social1")
                .fullUrlPlatform("insta.com.br")
                .build();
        List<SocialMediaEntity> lista = List.of(social, social);
        return lista;
    }

    public static ReservationEntity registrarReserva(){
        return ReservationEntity.builder()
                .id(geradorId())
                .date(LocalDate.now())
                .hasCancelled(false)
                .numberOfPeople(10)
                .showedUp(true)
                .build();

    }
    public static List<ReservationEntity> registrarReservas(){
        var reserva = registrarReserva();
        List<ReservationEntity> lista = List.of(reserva);
        return lista;

    }
    public static Long geradorId(){
        Random r = new Random();
        return r.nextLong();
    }

}
