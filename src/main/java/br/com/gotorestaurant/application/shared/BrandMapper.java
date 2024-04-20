package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.BrandEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class BrandMapper {

    private BrandMapper() {}

    public static BrandEntity toBrandEntity(br.com.gotorestaurant.core.records.Brand brand) {
        BrandEntity brandEntityJPAEntity = new BrandEntity();
        brandEntityJPAEntity.setPathImageBasic(brand.pathImageBasic());
        brandEntityJPAEntity.setPathImageDark(brand.pathImageDark());

        return brandEntityJPAEntity;
    }

    public static br.com.gotorestaurant.core.records.Brand  toBrand(BrandEntity brandEntityJPAEntity) {
        return new br.com.gotorestaurant.core.records.Brand(brandEntityJPAEntity.getPathImageBasic(), brandEntityJPAEntity.getPathImageDark());
    }
}
