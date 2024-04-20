package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "brands", schema = "gotorestaurant")
public class BrandEntity {

    @Id
    @UuidGenerator
    private UUID uuid;

    @OneToOne
    private RestaurantEntity restaurantEntity;

    private String pathImageBasic;

    private String pathImageDark;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
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
