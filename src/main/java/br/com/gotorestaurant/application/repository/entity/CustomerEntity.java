package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity(name = "Customer")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers", schema = "gotorestaurant")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RestaurantEntity> restaurantFK;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
    private List<SocialMediaEntity> socialMedia;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
    private List<PhoneEntity> phones;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
    private List<ReservationEntity> reservations;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String document;

}
