package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.BrandEntity;
import br.com.gotorestaurant.core.records.Brand;
import org.springframework.stereotype.Component;

@Component
public abstract class BrandMapper {

    private BrandMapper() {}

    public static BrandEntity toBrandEntity(Brand brand) {
        if (brand == null) return null;
        BrandEntity brandEntityEntity = new BrandEntity();
        brandEntityEntity.setPathImageBasic(brand.pathImageBasic());
        brandEntityEntity.setPathImageDark(brand.pathImageDark());

        return brandEntityEntity;
    }

    public static Brand toBrand(BrandEntity entity) {
        if (entity == null) return null;
        return new Brand(entity.getPathImageBasic(), entity.getPathImageDark());
    }
}
