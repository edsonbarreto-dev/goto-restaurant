package br.com.gotorestaurant.application.shared;

import br.com.gotorestaurant.application.repository.entity.BrandEntityJPA;
import org.springframework.stereotype.Component;

@Component
public abstract class BrandMapper {

    private BrandMapper() {}

    public static BrandEntityJPA toBrandEntity(br.com.gotorestaurant.core.records.Brand brand) {
        BrandEntityJPA brandEntityJPAEntity = new BrandEntityJPA();
        brandEntityJPAEntity.setPathImageBasic(brand.pathImageBasic());
        brandEntityJPAEntity.setPathImageDark(brand.pathImageDark());

        return brandEntityJPAEntity;
    }

    public static br.com.gotorestaurant.core.records.Brand  toBrand(BrandEntityJPA brandEntityJPAEntity) {
        return new br.com.gotorestaurant.core.records.Brand(brandEntityJPAEntity.getPathImageBasic(), brandEntityJPAEntity.getPathImageDark());
    }
}
