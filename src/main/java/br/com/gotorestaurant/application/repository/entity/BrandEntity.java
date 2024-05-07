package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "brands", schema = "gotorestaurant")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private RestaurantEntity restaurantEntity;

    private String pathImageBasic;

    private String pathImageDark;

    public BrandEntity(Long id, RestaurantEntity restaurantEntity, String pathImageBasic, String pathImageDark) {
        this.id = id;
        this.restaurantEntity = restaurantEntity;
        this.pathImageBasic = pathImageBasic;
        this.pathImageDark = pathImageDark;
    }

    public BrandEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity_id) {
        this.restaurantEntity = restaurantEntity_id;
    }

    public String getPathImageBasic() {
        return pathImageBasic;
    }

    public void setPathImageBasic(String pathImageBasic) {
        this.pathImageBasic = pathImageBasic;
    }

    public String getPathImageDark() {
        return pathImageDark;
    }

    public void setPathImageDark(String pathImageDark) {
        this.pathImageDark = pathImageDark;
    }
}
