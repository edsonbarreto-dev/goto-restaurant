package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.BrandEntity;
import br.com.gotorestaurant.core.records.Brand;
import org.springframework.stereotype.Component;

@Component
public abstract class BrandMapper {

    private BrandMapper() {}

    public static BrandEntity toBrandEntity(Brand brand) {
        BrandEntity brandEntityEntity = new BrandEntity();
        brandEntityEntity.setPathImageBasic(brand.pathImageBasic());
        brandEntityEntity.setPathImageDark(brand.pathImageDark());

        return brandEntityEntity;
    }

    public static Brand toBrand(BrandEntity entity) {
        return new Brand(entity.getPathImageBasic(), entity.getPathImageDark());
    }
}
