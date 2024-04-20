package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.BrandEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class BrandMapper {

    private BrandMapper() {}

    public static BrandEntity toBrandEntity(br.com.gotorestaurant.core.records.Brand brand) {
        BrandEntity brandEntityEntity = new BrandEntity();
        brandEntityEntity.setPathImageBasic(brand.pathImageBasic());
        brandEntityEntity.setPathImageDark(brand.pathImageDark());

        return brandEntityEntity;
    }

    public static br.com.gotorestaurant.core.records.Brand  toBrand(BrandEntity brandEntityEntity) {
        return new br.com.gotorestaurant.core.records.Brand(brandEntityEntity.getPathImageBasic(), brandEntityEntity.getPathImageDark());
    }
}
