package br.com.gotorestaurant.application.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "BRAND", schema = "TOGORESTAURANT")
public class BrandEntityJPA {

    @Id
    @UuidGenerator
    private UUID uuid;

    private String pathImageBasic;

    private String pathImageDark;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
