package br.com.gotorestaurant.utils;

import br.com.gotorestaurant.application.repository.entity.*;
import br.com.gotorestaurant.core.entity.Restaurant;
import br.com.gotorestaurant.core.enums.CountryCodeEnum;
import br.com.gotorestaurant.core.enums.GenderEnum;
import br.com.gotorestaurant.core.enums.WorkFunctionEnum;
import br.com.gotorestaurant.core.records.Address;
import br.com.gotorestaurant.core.records.Brand;
import br.com.gotorestaurant.core.records.Phone;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class RestaurantHelper {
    public static RestaurantEntity registerRestaurant() {
        return RestaurantEntity.builder()
                .document("123456789")
                .name("Restaurante Mocoto")
                .capacity(300)
//                .addressEntity(registerAddress())
//                .phoneEntity(registerPhone())
//                .brandEntity(registerBrand())
                .build();
    }

    public static Restaurant cadastrarRestaurante() {
        Restaurant restaurant = new Restaurant( "987654321","Restaurante Salsa", 200);
        restaurant.setBrand(new Brand("imagem1", "imagem2"));
        restaurant.setAddress(new Address("Rua alcantara", "23", "Vila Maria", "São Paulo", "SP", "Brasil", "02211200"));
        Phone phone = new Phone(CountryCodeEnum.BRAZIL, "11",854874585L);
        restaurant.setPhones(List.of(phone));
        return restaurant;
    }



    public static BrandEntity registerBrand(){
        return BrandEntity.builder()
                .pathImageBasic("image1")
                .pathImageDark("image2")
                .build();

    }

    public static List<AddressEntity> registerAddress(){
        AddressEntity address = AddressEntity.builder()
                .publicPlace("Avenida Paulista")
                .number("12")
                .neighborhood("neighborhood")
                .city("SP")
                .state("SP")
                .country("Brasil")
                .zipCode("01310914")
                .build();
        List<AddressEntity> lista = List.of(address);
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
                .commentEntity(registerComment())
                .build();
        List<SocialMediaEntity> lista = List.of(social, social);
        return lista;
    }
    public static List<CommentEntity> registerComment(){
        CommentEntity commentEntity = CommentEntity.builder()
                .message("mensagem1")
                .build();
        List<CommentEntity> lista = new ArrayList<>();
        lista.add(commentEntity);
        lista.add(commentEntity);
        return lista;

    }

    public static List<EmployeeEntity> registerEmployee(){

        EmployeeEntity employee = EmployeeEntity.builder()
                .name("Employee1")
                .document("123456688")
                .workFunction(WorkFunctionEnum.CAIXA)
                .workSchedule("schedule")
                .socialMediaEntity(registerSocialMedia())
                .build();
        List<EmployeeEntity> lista = List.of(employee);
        return lista;
    }

    public static List<CustomerEntity> registerCustomers(){
        CustomerEntity customer = CustomerEntity.builder()
                .name("customer1")
                .document("379483938")
                .email("customer1@gmail.com")
//                .socialMediaEntity(registerSocialMedia())
//                .phoneEntity(registerPhone())
                .build();
        List<CustomerEntity> lista = List.of(customer);
        return lista;
    }

    public static CustomerEntity registerCustomer(){
        return CustomerEntity.builder()
                .name("customer1")
                .document("379483938")
                .email("customer1@gmail.com")
//                .socialMediaEntity(registerSocialMedia())
//                .phoneEntity(registerPhone())
                .build();

    }

    public static List<SupplierEntity> registerSuplier(){
        SupplierEntity supplier = SupplierEntity.builder()
                .name("supplier")
                .document("379483938")
                .email("supplier@gmail.com")
//                .socialMediaEntity(registerSocialMedia())
//                .phoneEntity(registerPhone())
                .build();
        List<SupplierEntity> lista = List.of(supplier);
        return lista;
    }

    public static List<PartnerEntity> registerPartner(){
        PartnerEntity partner = PartnerEntity.builder()
                .name("supplier")
                .document("379483938")
                .email("supplier@gmail.com")
//                .socialMediaEntity(registerSocialMedia())
//                .phoneEntity(registerPhone())
                .build();
        List<PartnerEntity> lista = List.of(partner);
        return lista;
    }

    public static BirthdayPersonEntity registrarNiver(){
        return BirthdayPersonEntity.builder()
                .name("Maria")
                .age(30)
                .birthday(10)
                .birthdayMonth(3)
                .gender(GenderEnum.FEMININE)
                .wishHouseCelebration(true)
                .build();
    }

    public static List<BirthdayPersonEntity> registrarNivers(){
        BirthdayPersonEntity niver = BirthdayPersonEntity.builder()
                .name("Maria")
                .age(30)
                .birthday(10)
                .birthdayMonth(3)
                .gender(GenderEnum.FEMININE)
                .wishHouseCelebration(true)
                .build();
        List<BirthdayPersonEntity> lista = List.of(niver);
        return lista;
    }

    public static ReservationEntity registarReserva(){
        return ReservationEntity.builder()
                .customerEntity(registerCustomer())
                .date(LocalDate.now())
                .hasCancelled(false)
                .numberOfPeople(10)
                .showedUp(true)
                .birthdaysPersonEntity(registrarNivers())
                .build();

    }

    public static Long geradorId(){
        Random r = new Random();
        return r.nextLong();
    }

    public static String createTemporaryToken(String username) {
        // Defina a chave secreta para assinar o token (pode ser qualquer string)
        String secretKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgVm9sbC5tZWQiLCJzdWIiOiJtYXJpb24iLCJpZCI6MSwiZXhwIjoxNzE1MTI1NjAxfQ.QkX-fbjXSBDsKAQxSP1kA_3FQ1TOxJzaq6Q3co5sH_E";

        // Crie o token JWT
        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }
}